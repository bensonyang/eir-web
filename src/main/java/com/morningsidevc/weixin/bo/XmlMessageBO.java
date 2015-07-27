package com.morningsidevc.weixin.bo;

import com.morningsidevc.weixin.enums.ReplyTypeEnum;
import com.morningsidevc.weixin.replymessage.util.MsgConvertUtil;
import com.morningsidevc.weixin.replymessage.xml.XmlNews;
import com.morningsidevc.weixin.replymessage.xml.XmlText;

import java.util.Date;
import java.util.Map;

/**
 * liang.liu
 */
public class XmlMessageBO {

    // 返回图文消息（无内容）
    public static XmlNews prepareXmlNews(Map<String, String> requestMap) {
        // 发送方帐号（open_id）
        String fromUserName = requestMap.get("FromUserName");
        // 公众帐号
        String toUserName = requestMap.get("ToUserName");

        // 回复图文消息
        XmlNews xmlNews = new XmlNews();
        xmlNews.setToUserName(fromUserName);
        xmlNews.setFromUserName(toUserName);
        xmlNews.setCreateTime(new Date().getTime());
        xmlNews.setMsgType(ReplyTypeEnum.NEWS.getValue());
        xmlNews.setFuncFlag(0);

        return xmlNews;

    }

    // 返回文本消息（无内容）
    public static XmlText prepareXmlText(Map<String, String> requestMap) {

        // 发送方帐号（open_id）
        String fromUserName = requestMap.get("FromUserName");
        // 公众帐号
        String toUserName = requestMap.get("ToUserName");

        XmlText xmlText = new XmlText();

        xmlText.setToUserName(fromUserName);
        xmlText.setFromUserName(toUserName);
        xmlText.setCreateTime(new Date().getTime());
        xmlText.setMsgType(ReplyTypeEnum.TEXT.getValue());
        xmlText.setFuncFlag(0);

        return xmlText;
    }

    // 返回异常或者没有处理结果时的默认文本回复消息
    public static String getUnAvailableReply(Map<String, String> requestMap) {

        XmlText xmlText = prepareXmlText(requestMap);
        xmlText.setContent("哎呀，暂时没有结果，请稍后再试");
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);

    }

}
