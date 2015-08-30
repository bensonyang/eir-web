package com.morningsidevc.wechart.processor.message;

import com.morningsidevc.wechart.bo.WeChartAddFeedBO;
import com.morningsidevc.wechart.processor.WeChartBaseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 简单的文本消息处理器，只是简单返回配置的响应内容 Created by shichao.liao on 14-8-27.
 */
public class TextMsgProcessor extends WeChartBaseProcessor {

    private final static Logger logger = LoggerFactory.getLogger(TextMsgProcessor.class);

    @Override
    public String processRequest(Map<String, String> requestMap) {

        if (WeChartAddFeedBO.isAddFeedMode(requestMap)) {
            return WeChartAddFeedBO.processInAddFeedMode(requestMap);
        }

        return "";
    }

}
