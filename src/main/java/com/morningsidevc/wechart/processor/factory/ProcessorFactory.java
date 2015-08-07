package com.morningsidevc.wechart.processor.factory;


import com.morningsidevc.wechart.processor.WeChartProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liang.liu on 15-7-1.
 */
public abstract class ProcessorFactory {

    protected Map<String, WeChartProcessor> processors = new HashMap<String, WeChartProcessor>();

    public abstract WeChartProcessor loadWeiXinProcessor(Map<String, String> requestMap);

    public void setProcessors(Map<String, WeChartProcessor> processors) {
        this.processors = processors;
    }
}
