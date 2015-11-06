package com.morningsidevc.wechart.service.impl;

import com.morningsidevc.enums.WeiXinType;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.utils.Constants;
import com.morningsidevc.utils.MapCacheUtils;
import com.morningsidevc.vo.Feed;
import com.morningsidevc.vo.WebPageMsgBody;
import com.morningsidevc.vo.WeiboMsgBody;
import com.morningsidevc.wechart.bo.RedirectBO;
import com.morningsidevc.wechart.replymessage.xml.Article;
import com.morningsidevc.wechart.service.WeChartFeedService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-29, Time: 下午3:35
 */
@Component
public class WeChartFeedServiceImpl implements WeChartFeedService {

    private final static Logger logger = LoggerFactory.getLogger(WeChartFeedServiceImpl.class);

    private final static int FEED_LIST_LENGTH_LIMIT = 6;

    @Resource
    private FeedInfoService feedInfoService;


    @Override
    public List<Article> getLastFeedInfo() {
        List<Article> articleList = MapCacheUtils.getInstance().get(Constants.CACHE_KEY_LAST_FEED);
        if (!CollectionUtils.isEmpty(articleList)) {
            return articleList;
        }

        synchronized (WeChartFeedServiceImpl.class) {
            articleList = MapCacheUtils.getInstance().get(Constants.CACHE_KEY_LAST_FEED);
            if (!CollectionUtils.isEmpty(articleList)) {
                return articleList;
            }
            articleList = generateLastFeedInfo();
            if (!CollectionUtils.isEmpty(articleList)) {
                MapCacheUtils.getInstance().add(Constants.CACHE_KEY_LAST_FEED, articleList, Constants.CACHE_TIME_LAST_FEED);
            }
        }

        return articleList;
    }

    private List<Article> generateLastFeedInfo() {
        logger.info("load last feed info !");

        List<Article> articleList = new ArrayList<Article>();

        List<Feed> feedList;
        try {
            feedList = feedInfoService.findFeeds(0, 10, 0, 0, "");
        } catch (Exception e) {
            logger.error("feedInfoService get last feed error!", e);
            return articleList;
        }

        if (CollectionUtils.isEmpty(feedList)) {
            logger.info("load last feed info is empty !");
            return articleList;
        }

        Article titleArticle = new Article();
        titleArticle.setTitle("最新动态");
        // TODO 换头图
        titleArticle.setPicUrl("http://i3.s1.dpfile.com/pc/7_matJFWy2bveJGcM7xUOeHxPoTQBvfaqdlwGWhfgwr_yWiz3YvPjow2pe16n4-vFdqKbhCjm3KCxmX2CI7XEg.jpg");
        articleList.add(titleArticle);

        for (Feed feed : feedList) {
            if (feed == null || feed.getMsgBody() == null) {
                continue;
            }
            String title = generateArticleTitle(feed);
            if (StringUtils.isBlank(title)) {
                continue;
            }

            Article feedArticle = new Article();
            feedArticle.setTitle(title);
            if (StringUtils.isNotBlank(feed.getAuthor().getAvatarUrl())) {
                if (feed.getAuthor().getAvatarUrl().startsWith("http://")) {
                    feedArticle.setPicUrl(feed.getAuthor().getAvatarUrl());
                } else {
                    feedArticle.setPicUrl(Constants.DOMAIN + feed.getAuthor().getAvatarUrl());
                }
            } else {
                // TODO 换默认头像
                feedArticle.setPicUrl("http://i2.dpfile.com/s/img/uc/default-avatar120c120.png");
            }
            try {
                feedArticle.setUrl(RedirectBO.generateUserAuthorizeUrl("http://www.msvcplus.com/mfeed?feedId=" + feed.getFeedId(), WeiXinType.WECHAT));
            } catch (UnsupportedEncodingException e) {
                logger.error("generate detail feed url error!", e);
            }

            articleList.add(feedArticle);

            if (articleList.size() >= FEED_LIST_LENGTH_LIMIT) {
                break;
            }
        }

        Article endArticle = new Article();
        endArticle.setTitle("查看更多动态");
        try {
            endArticle.setUrl(RedirectBO.generateUserAuthorizeUrl("http://www.msvcplus.com/community", WeiXinType.WECHAT));
        } catch (UnsupportedEncodingException e) {
            logger.error("generate all feed url error!", e);
        }
        articleList.add(endArticle);

        return articleList;
    }


    private String generateArticleTitle(Feed feed) {
        StringBuilder titleBuilder = new StringBuilder();
        // int limit = StringUtils.isBlank(feed.getAuthor().getAvatarUrl()) ? 30 : 25;
        int limit = 26;
        if (feed.getFeedType() == 0) {
            WeiboMsgBody weiboMsgBody = (WeiboMsgBody) feed.getMsgBody();
            if (weiboMsgBody != null && StringUtils.isNotBlank(weiboMsgBody.getContent())) {
                titleBuilder.append(weiboMsgBody.getContent().substring(0, limit > weiboMsgBody.getContent().length() ? weiboMsgBody.getContent().length() : limit));
            }
            if (limit < weiboMsgBody.getContent().length()) {
                titleBuilder.append("...");
            }
        } else if (feed.getFeedType() == 1) {
            WebPageMsgBody webPageMsgBody = (WebPageMsgBody) feed.getMsgBody();
            if (webPageMsgBody != null && StringUtils.isNotBlank(webPageMsgBody.getTitle())) {
                titleBuilder.append(webPageMsgBody.getTitle().substring(0, limit > webPageMsgBody.getTitle().length() ? webPageMsgBody.getTitle().length() : limit));
            }
            if (limit < webPageMsgBody.getTitle().length()) {
                titleBuilder.append("...");
            }
        }

        return titleBuilder.toString();
    }
}
