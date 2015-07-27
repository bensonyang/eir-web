package com.morningsidevc.weixin.processor.factory;


import com.morningsidevc.weixin.processor.WeiXinProcessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liang.liu on 15-7-1.
 */
public abstract class ProcessorFactory {

    protected Map<String, WeiXinProcessor> processors = new HashMap<String, WeiXinProcessor>();

    public abstract WeiXinProcessor loadWeiXinProcessor(Map<String, String> requestMap);

    public void setProcessors(Map<String, WeiXinProcessor> processors) {
        this.processors = processors;
    }
}
