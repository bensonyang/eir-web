package com.morningsidevc.weixin.processor.message;

import com.morningsidevc.weixin.bo.XmlMessageBO;
import com.morningsidevc.weixin.processor.WeiXinBaseProcessor;
import com.morningsidevc.weixin.replymessage.util.MsgConvertUtil;
import com.morningsidevc.weixin.replymessage.xml.XmlText;

import java.util.Map;

/**
 * 简单的文本消息处理器，只是简单返回配置的响应内容 Created by shichao.liao on 14-8-27.
 */
public class TextMsgProcessor extends WeiXinBaseProcessor {

    @Override
    public String processRequest(Map<String, String> requestMap) {

        String jsContent = "欢迎进入技师模式，目前技师模式只提供与技师文本聊天功能，谢谢！";
        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent(jsContent);
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);
    }

}
