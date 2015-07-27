package com.morningsidevc.weixin.processor.factory;


import com.morningsidevc.utils.SpringLocator;
import com.morningsidevc.weixin.enums.MsgTypeEnum;

/**
 * Created by shichao.liao on 14-9-1.
 */
public class ProcessorFactorySelecter {

    public static ProcessorFactory selectProcessorFactory(String msgType) {

        if (MsgTypeEnum.EVENT.getValue().equalsIgnoreCase(msgType)) {
            return SpringLocator.getBean("eventProcessFactory");
        } else {
            return SpringLocator.getBean("messageProcessFactory");
        }
    }

}
