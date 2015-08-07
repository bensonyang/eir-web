package com.morningsidevc.wechart.processor.message;

import com.morningsidevc.wechart.bo.XmlMessageBO;
import com.morningsidevc.wechart.processor.WeChartBaseProcessor;
import com.morningsidevc.wechart.replymessage.util.MsgConvertUtil;
import com.morningsidevc.wechart.replymessage.xml.XmlText;

import java.util.Map;

/**
 * 默认的文本消息处理器 Created by shichao.liao on 14-9-5.
 */
public class DefaultMsgProcessor extends WeChartBaseProcessor {

    @Override
    public String processRequest(Map<String, String> requestMap) {

        String jsContent = "欢迎使用，请输入你要发表的说说或推荐的网址链接，谢谢！";
        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent(jsContent);
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);

    }

}
