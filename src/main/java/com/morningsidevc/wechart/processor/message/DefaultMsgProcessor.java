package com.morningsidevc.wechart.processor.message;

import com.morningsidevc.wechart.processor.WeChartBaseProcessor;

import java.util.Map;

/**
 * 默认的文本消息处理器 Created by shichao.liao on 14-9-5.
 */
public class DefaultMsgProcessor extends WeChartBaseProcessor {

    @Override
    public String processRequest(Map<String, String> requestMap) {

        /*
        String jsContent = "success";
        XmlText xmlText = XmlMessageBO.prepareXmlText(requestMap);
        xmlText.setContent(jsContent);
        return MsgConvertUtil.parseMsg2XMLStr(xmlText);
        */

        return "success";

    }

}
