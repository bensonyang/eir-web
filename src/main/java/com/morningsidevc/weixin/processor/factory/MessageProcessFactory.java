package com.morningsidevc.weixin.processor.factory;

import com.morningsidevc.utils.SpringLocator;
import com.morningsidevc.weixin.enums.MsgTypeEnum;
import com.morningsidevc.weixin.processor.WeiXinProcessor;

import java.util.Map;

/**
 * 消息分发器 Created by shichao.liao on 14-9-4.
 */
public class MessageProcessFactory extends ProcessorFactory {

    @Override
    public WeiXinProcessor loadWeiXinProcessor(Map<String, String> requestMap) {
        String msgType = requestMap.get("MsgType");

        WeiXinProcessor processor;
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
