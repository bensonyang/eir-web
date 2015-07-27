package com.morningsidevc.weixin.handler;

import com.morningsidevc.weixin.processor.WeiXinProcessor;
import com.morningsidevc.weixin.processor.factory.ProcessorFactory;
import com.morningsidevc.weixin.processor.factory.ProcessorFactorySelecter;
import com.morningsidevc.weixin.utils.WeiXinUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WeixinMsgHandler {

    private final static Logger logger = Logger.getLogger(WeixinMsgHandler.class);

    private Map<String, String> requestData;

    public String dispose(HttpServletRequest request) {

        beforeExecute(request);

        String result = execute(request);

        afterExecute(request);

        return result;
    }

    private void beforeExecute(HttpServletRequest request) {
        requestData = WeiXinUtil.parseXml(request);
    }

    private  String execute(HttpServletRequest request) {
        if (CollectionUtils.isEmpty(requestData)) {
            return StringUtils.EMPTY;
        }

        logger.info("dispatchAndDisposeRequest start to process request:" + requestData.toString());


        WeiXinProcessor processor = matchProcessor(requestData);

        if (processor == null) {
            logger.info("dispatchAndDisposeRequest: no available processor for request#" + requestData.toString());
            return StringUtils.EMPTY;
        }

        String result = processor.process(requestData);

        return result;
    }

    private void afterExecute(HttpServletRequest request) {
    }

    private WeiXinProcessor matchProcessor(Map<String, String> requestData) {
        String msgType = requestData.get("MsgType");
        ProcessorFactory processorFactory = ProcessorFactorySelecter.selectProcessorFactory(msgType);
        return processorFactory.loadWeiXinProcessor(requestData);
    }

}
