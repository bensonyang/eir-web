package com.morningsidevc.wechart.processor;

import java.util.Map;

/**
 * liang.liu
 */
public abstract class WeChartBaseProcessor implements WeChartProcessor {


    protected void beforeProcessRequest(Map<String, String> requestMap) {
    }

    protected abstract String processRequest(Map<String, String> requestMap);

    @Override
    public String process(Map<String, String> requestMap) {

        beforeProcessRequest(requestMap);

        String respMessage = processRequest(requestMap);

        afterProcessRequest(requestMap);

        return respMessage;
    }

    protected void afterProcessRequest(Map<String, String> requestMap) {
    }

}
