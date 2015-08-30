package com.morningsidevc.wechart.processor.event;

import com.morningsidevc.enums.WeiXinType;
import com.morningsidevc.po.gen.WeixinUserInfo;
import com.morningsidevc.service.WeixinUserService;
import com.morningsidevc.wechart.bo.RedirectBO;
import com.morningsidevc.wechart.bo.XmlMessageBO;
import com.morningsidevc.wechart.po.WeChartUser;
import com.morningsidevc.wechart.processor.WeChartBaseProcessor;
import com.morningsidevc.wechart.replymessage.util.MsgConvertUtil;
import com.morningsidevc.wechart.replymessage.xml.Article;
import com.morningsidevc.wechart.replymessage.xml.XmlNews;
import com.morningsidevc.wechart.service.WeChartUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-29, Time: 下午3:11
 */
public abstract class AddFeedClickEventBaseProcessor extends WeChartBaseProcessor {

    private final static Logger logger = LoggerFactory.getLogger(AddFeedClickEventBaseProcessor.class);

    @Resource
    private WeixinUserService weixinUserService;
    @Resource
    private WeChartUserService weChartUserService;

    @Override
    public String processRequest(Map<String, String> requestMap) {

        String openId = requestMap.get("FromUserName");
        WeChartUser weChartUser = weChartUserService.getUserInfoByOpenId(openId);
        if (weChartUser == null) {
            logger.info("weChartUser is null!");
            return MsgConvertUtil.parseMsg2XMLStr(generateNoBandMessage(requestMap));
        }
        logger.info("weChartUser info: " + weChartUser.toString());

        WeixinUserInfo weixinUserInfo = weixinUserService.getWeixinUserInfoByUnionid(weChartUser.getUnionid());
        if (weixinUserInfo == null) {
            logger.info("weixinUserInfo is null!");
            return MsgConvertUtil.parseMsg2XMLStr(generateNoBandMessage(requestMap));
        }

        logger.info("click add feed response: " + enterAddFeedMode(requestMap));

        return enterAddFeedMode(requestMap);
    }

    private XmlNews generateNoBandMessage(Map<String, String> requestMap) {

        List<Article> articles = new ArrayList<Article>();

        Article titleArticle = new Article();
        titleArticle.setTitle("权限不足");
        titleArticle.setDescription("请绑定账号，点击跳转绑定账号页面");
        titleArticle.setPicUrl("");
        try {
            // TODO 绑定成功页面URL
            String toUrl = RedirectBO.generateUserAuthorizeUrl("http://www.msvcplus.com/bindsuccess", WeiXinType.WECHAT);
            titleArticle.setUrl(toUrl);
        } catch (UnsupportedEncodingException e) {
            logger.error("generate bind url error!", e);
        }

        articles.add(titleArticle);

        XmlNews xmlNews = XmlMessageBO.prepareXmlNews(requestMap);
        xmlNews.setArticles(articles);
        xmlNews.setArticleCount(articles.size());

        return xmlNews;
    }

    protected abstract String enterAddFeedMode(Map<String, String> requestMap);
}
