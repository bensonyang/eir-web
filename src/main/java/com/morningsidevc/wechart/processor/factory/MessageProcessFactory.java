package com.morningsidevc.wechart.processor.factory;

import com.morningsidevc.utils.SpringLocator;
import com.morningsidevc.wechart.enums.MsgTypeEnum;
import com.morningsidevc.wechart.processor.WeChartProcessor;

import java.util.Map;

/**
 * 消息分发器 Created by shichao.liao on 14-9-4.
 */
public class MessageProcessFactory extends ProcessorFactory {

    @Override
    public WeChartProcessor loadWeiXinProcessor(Map<String, String> requestMap) {
        String msgType = requestMap.get("MsgType");

        WeChartProcessor processor;
        if (MsgTypeEnum.TEXT.getValue().equalsIgnoreCase(msgType)) {
            processor = processors.get(msgType.toLowerCase());
        } else if (MsgTypeEnum.LINK.getValue().equalsIgnoreCase(msgType)) {
            processor = processors.get(msgType.toLowerCase());
        } else {
            processor = SpringLocator.getBean("defaultMsgProcessor");
        }

        return processor;
    }

}
