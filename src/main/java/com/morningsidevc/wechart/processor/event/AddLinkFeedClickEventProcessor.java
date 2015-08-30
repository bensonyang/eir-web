package com.morningsidevc.wechart.processor.event;

import com.morningsidevc.wechart.bo.WeChartAddFeedBO;

import java.util.Map;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-29, Time: 下午3:11
 */
public class AddLinkFeedClickEventProcessor extends AddFeedClickEventBaseProcessor {


    @Override
    protected String enterAddFeedMode(Map<String, String> requestMap) {
        return WeChartAddFeedBO.enterAddFeedMode(requestMap);
    }
}
