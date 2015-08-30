package com.morningsidevc.wechart.service.impl;

import com.morningsidevc.wechart.bo.WeChartMessageBO;
import com.morningsidevc.wechart.service.WeChartMessageService;
import com.morningsidevc.wechart.service.WeChartTokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-3, Time: 上午12:08
 */
@Component
public class WeChartMessageServiceImpl implements WeChartMessageService {

    @Resource
    private WeChartTokenService weChartTokenService;

    @Override
    public boolean sendCommentTemplateMessage(String toOpenId, String toUrl, String fromUserName, String commentTime, String commentContent) {
        if (StringUtils.isBlank(toOpenId)) {
            return false;
        }
        String accessToken = weChartTokenService.getWeiXinAccessToken();
        if (StringUtils.isBlank(accessToken)) {
            return false;
        }
        String text = WeChartMessageBO.generateCommentTemplateData(toOpenId, toUrl, fromUserName, commentTime, commentContent);
        if (StringUtils.isBlank(text)) {
            return false;
        }

        return WeChartMessageBO.sendTemplateMessage(accessToken, text);
    }
}
