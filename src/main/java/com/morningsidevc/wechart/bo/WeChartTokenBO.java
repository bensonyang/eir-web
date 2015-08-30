package com.morningsidevc.wechart.bo;

import com.google.gson.Gson;
import com.morningsidevc.enums.WeiXinType;
import com.morningsidevc.utils.RequestUtils;
import com.morningsidevc.wechart.po.WeChartTokenContainer;
import com.morningsidevc.wechart.po.WeChartTokenResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shichao.liao on 14-10-15.
 */
public class WeChartTokenBO {

    private final static Logger logger = LoggerFactory.getLogger(WeChartTokenBO.class);

    private static final String WECHART_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";

    private static WeChartTokenContainer weChartTokenContainer;

    public static String getWechartAccessToken() {
        long currentTime = new Date().getTime();
        if (weChartTokenContainer != null && (currentTime <= weChartTokenContainer.getExpiresTimestamp())) {
            return weChartTokenContainer.getAccessToken();
        }

        synchronized (WeChartTokenBO.class) {
            if (weChartTokenContainer != null && (currentTime <= weChartTokenContainer.getExpiresTimestamp())) {
                return weChartTokenContainer.getAccessToken();
            } else {
                WeChartTokenResponse weChartTokenResponse = getRemoteAccessTokenResponse();
                if (weChartTokenResponse != null && StringUtils.isNotBlank(weChartTokenResponse.getAccess_token())) {
                    long delayTime = (NumberUtils.toInt(weChartTokenResponse.getExpires_in(), 7200) - 600) * 1000;
                    weChartTokenContainer = new WeChartTokenContainer(weChartTokenResponse.getAccess_token(), currentTime + delayTime);
                    return weChartTokenContainer.getAccessToken();
                }
            }
        }

        return "";
    }

    /**
     * 获取对应的AccessToken响应,附带有状态信息
     *
     * @return AccessToken响应
     */
    private static WeChartTokenResponse getRemoteAccessTokenResponse() {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", WeiXinType.WECHAT.getAppid());
        paramMap.put("secret", WeiXinType.WECHAT.getSecret());
        String queryString = RequestUtils.generateQueryParam(paramMap);
        String result = RequestUtils.doGet(WECHART_ACCESS_TOKEN_URL, queryString);

        if (result.contains("errcode")) {
            logger.info("get wechart token error! response:" + result);
            return null;
        }

        Gson gson = new Gson();
        WeChartTokenResponse tokenResponse = gson.fromJson(result, WeChartTokenResponse.class);
        return tokenResponse;
    }

}
