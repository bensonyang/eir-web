package com.morningsidevc.wechart.processor;

import java.util.Map;

/**
 * liang.liu
 */
public interface WeChartProcessor {

    /**
     * 处理微信请求，返回处理结果
     *
     * @param requestMap
     * @return
     */
    public String process(Map<String, String> requestMap);
}
