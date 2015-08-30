package com.morningsidevc.wechart.service.impl;

import com.morningsidevc.wechart.bo.WeChartUserInfoBO;
import com.morningsidevc.wechart.po.WeChartUser;
import com.morningsidevc.wechart.service.WeChartTokenService;
import com.morningsidevc.wechart.service.WeChartUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-3, Time: 上午12:04
 */
@Component
public class WeChartUserServiceImpl implements WeChartUserService {

    @Resource
    private WeChartTokenService weChartTokenService;

    @Override
    public WeChartUser getUserInfoByOpenId(String openId) {
        String accessToken = weChartTokenService.getWeiXinAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        return WeChartUserInfoBO.getUserInfo(accessToken, openId);
    }
}
