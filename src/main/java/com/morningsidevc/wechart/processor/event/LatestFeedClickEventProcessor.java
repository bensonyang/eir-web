package com.morningsidevc.wechart.processor.event;

import com.morningsidevc.wechart.bo.XmlMessageBO;
import com.morningsidevc.wechart.processor.WeChartBaseProcessor;
import com.morningsidevc.wechart.replymessage.util.MsgConvertUtil;
import com.morningsidevc.wechart.replymessage.xml.Article;
import com.morningsidevc.wechart.replymessage.xml.XmlNews;
import com.morningsidevc.wechart.replymessage.xml.XmlText;
import com.morningsidevc.wechart.service.WeChartFeedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-29, Time: 下午3:11
 */
public class LatestFeedClickEventProcessor extends WeChartBaseProcessor {

    private final static Logger logger = LoggerFactory.getLogger(LatestFeedClickEventProcessor.class);

    @Resource
    private WeChartFeedService weChartFeedService;

    @Override
    public String processRequest(Map<String, String> requestMap) {


        List<Article> articles = weChartFeedService.getLastFeedInfo();

        if (CollectionUtils.isEmpty(articles)) {
            XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
            xmlText.setContent("哎呀，暂时没有结果，请稍后再试");
            return MsgConvertUtil.parseMsg2XMLStr(xmlText);
        }

        XmlNews xmlNews = XmlMessageBO.prepareXmlNews(requestMap);
        xmlNews.setArticles(articles);
        xmlNews.setArticleCount(articles.size());

        return MsgConvertUtil.parseMsg2XMLStr(xmlNews);
    }
}
