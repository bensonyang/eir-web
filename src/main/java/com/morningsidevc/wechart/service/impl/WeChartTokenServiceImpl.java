package com.morningsidevc.wechart.service.impl;

import com.morningsidevc.wechart.bo.WeChartTokenBO;
import com.morningsidevc.wechart.service.WeChartTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-23, Time: 下午9:05
 */
@Component
public class WeChartTokenServiceImpl implements WeChartTokenService {

    private final static Logger logger = LoggerFactory.getLogger(WeChartTokenServiceImpl.class);

    @Override
    public String getWeiXinAccessToken() {
        return WeChartTokenBO.getWechartAccessToken();
    }

}
