package com.morningsidevc.wechart.handler;

import com.morningsidevc.wechart.processor.WeChartProcessor;
import com.morningsidevc.wechart.processor.factory.ProcessorFactory;
import com.morningsidevc.wechart.processor.factory.ProcessorFactorySelecter;
import com.morningsidevc.wechart.utils.WeChartUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WeChartMsgHandler {

    private final static Logger logger = LoggerFactory.getLogger(WeChartMsgHandler.class);

    private Map<String, String> requestData;

    public String dispose(HttpServletRequest request) {

        beforeExecute(request);

        String result = execute(request);

        afterExecute(request);

        return result;
    }

    private void beforeExecute(HttpServletRequest request) {
        requestData = WeChartUtil.parseXml(request);
    }

    private  String execute(HttpServletRequest request) {
        if (CollectionUtils.isEmpty(requestData)) {
            return StringUtils.EMPTY;
        }

        logger.info("dispatchAndDisposeRequest start to process request:" + requestData.toString());


        WeChartProcessor processor = matchProcessor(requestData);

        if (processor == null) {
            logger.info("dispatchAndDisposeRequest: no available processor for request#" + requestData.toString());
            return StringUtils.EMPTY;
        }

        String result = processor.process(requestData);
        logger.info("WeChartMsgHandler handle result: " + result);

        return result;
    }

    private void afterExecute(HttpServletRequest request) {
    }

    private WeChartProcessor matchProcessor(Map<String, String> requestData) {
        String msgType = requestData.get("MsgType");
        ProcessorFactory processorFactory = ProcessorFactorySelecter.selectProcessorFactory(msgType);
        return processorFactory.loadWeiXinProcessor(requestData);
    }

}
