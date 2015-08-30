package com.morningsidevc.wechart.processor.factory;

import com.morningsidevc.wechart.enums.EventTypeEnum;
import com.morningsidevc.wechart.processor.WeChartProcessor;

import java.util.Map;

/**
 * 事件分发器 Created by shichao.liao on 14-9-4.
 */
public class EventProcessFactory extends ProcessorFactory {

    @Override
    public WeChartProcessor loadWeiXinProcessor(Map<String, String> requestMap) {
        String eventType = requestMap.get("Event");
        String eventKey = requestMap.get("EventKey");

        StringBuffer processorKey = new StringBuffer(eventType.toLowerCase());

        if (EventTypeEnum.CLICK.getValue().equalsIgnoreCase(eventType)) {
            processorKey.append("_");
            processorKey.append(eventKey);
        }

        return processors.get(processorKey.toString());
    }

}
