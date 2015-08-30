package com.morningsidevc.wechart.bo;

import com.google.gson.Gson;
import com.morningsidevc.utils.RequestUtils;
import com.morningsidevc.wechart.po.WeChartUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-29, Time: 上午1:37
 */
public class WeChartUserInfoBO {

    private final static Logger logger = LoggerFactory.getLogger(WeChartUserInfoBO.class);

    private static final String WECHART_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";

    public static WeChartUser getUserInfo(String accessToken, String openId) {

        if (StringUtils.isBlank(openId) || StringUtils.isBlank(accessToken)) {
            logger.info("loadUserInfo openid is null");
            return null;
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("openid", openId);
        String queryString = RequestUtils.generateQueryParam(paramMap);

        String result = RequestUtils.doGet(WECHART_USER_INFO_URL, queryString);
        if (result.contains("errcode")) {
            logger.info("loadUserInfo response error openid:" + openId + " response:" + result);
            return null;
        }

        Gson gson = new Gson();
        WeChartUser userInfo = gson.fromJson(result, WeChartUser.class);

        logger.info("loadUserInfo success, openid:" + userInfo.getOpenid() + " unionid:" + userInfo.getUnionid() + " nickname:"
                + userInfo.getNickname());

        return userInfo;
    }


}
