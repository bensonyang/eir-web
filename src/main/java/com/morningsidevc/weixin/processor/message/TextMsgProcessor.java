package com.morningsidevc.weixin.processor.message;

import com.morningsidevc.crawler.HTMLBean;
import com.morningsidevc.crawler.HTMLCrawlerUtils;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.weixin.bo.XmlMessageBO;
import com.morningsidevc.weixin.processor.WeiXinBaseProcessor;
import com.morningsidevc.weixin.replymessage.util.MsgConvertUtil;
import com.morningsidevc.weixin.replymessage.xml.XmlText;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 简单的文本消息处理器，只是简单返回配置的响应内容 Created by shichao.liao on 14-8-27.
 */
public class TextMsgProcessor extends WeiXinBaseProcessor {

    private final static Logger logger = LoggerFactory.getLogger(TextMsgProcessor.class);

    @Resource
    private FeedInfoService feedInfoService;

    @Override
    public String processRequest(Map<String, String> requestMap) {

        String message = requestMap.get("Content");

        String replyMsg = null;
        if (StringUtils.isBlank(message)) {
            replyMsg = "请输入你要发表的说说或推荐的网址链接，谢谢！";
        } else {
            if (message.startsWith("http://") || message.startsWith("https://")) {
                HTMLBean html = HTMLCrawlerUtils.get(message);
                if (html == null || StringUtils.isBlank(html.getUrl()) || StringUtils.isBlank(html.getPageTitle()) || StringUtils.isBlank(html.getPageAbstract())) {
                    replyMsg = "获取推荐网址信息失败！";
                } else {
                    try {
                        feedInfoService.addFeed(3, html.getUrl(), html.getPageTitle(), html.getPageAbstract(), "");
                    } catch (Exception e) {
                        replyMsg = "抱歉，服务器添加推荐链接异常！";
                        logger.error("feedInfoService add link error!", e);
                    }
                }
            } else {
                try {
                    feedInfoService.addFeed(3, message, "");
                } catch (Exception e) {
                    replyMsg = "抱歉，服务器添加说说异常！";
                    logger.error("feedInfoService add message error!", e);
                }
            }
        }

        if (StringUtils.isBlank(replyMsg)) {
            replyMsg = "发表成功～～";
        }

        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent(replyMsg);
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);
    }

}
