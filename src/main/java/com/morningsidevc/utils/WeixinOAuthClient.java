package com.morningsidevc.utils;

import com.morningsidevc.po.WeixinUser;
import com.morningsidevc.po.WeixinUserToken;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-7-12, Time: 下午11:50
 */
public class WeixinOAuthClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(WeixinOAuthClient.class);


    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";

    private static final String GET_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";


    private static final String WEIXIN_APPID = "38d9e17b2";
    private static final String WEIXIN_SECRET = "cd5b3e75cd5ce5e9f1";



    /**
     * 根据code获取accesstoken
     *
     * @return
     */
    public static WeixinUserToken getWeixinUserToken(String authcode) {

        if (StringUtils.isBlank(authcode)) {
            return null;
        }

        Map<String ,String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", WEIXIN_APPID);
        paramMap.put("secret", WEIXIN_SECRET);
        paramMap.put("grant_type", "authorization_code");
        paramMap.put("code", authcode);

        String queryString = generateQueryParam(paramMap);

        String result = RequestUtils.doPost(ACCESS_TOKEN_URL, queryString);

        WeixinUserToken weixinUserToken = new WeixinUserToken();
        try {
            JSONObject accessInfo = new JSONObject(result);
            String accessToken = accessInfo.getString("access_token");
            String openId = accessInfo.getString("openid");
            int expiresIn = accessInfo.getInt("expires_in");
            String refreshToken = accessInfo.getString("refresh_token");
            String scope = accessInfo.getString("scope");
            String unionid = accessInfo.getString("unionid");

            if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId) || StringUtils.isBlank(unionid)) {
                return null;
            }

            weixinUserToken.setAccessToken(accessToken);
            weixinUserToken.setExpiresIn(expiresIn);
            weixinUserToken.setOpenId(openId);
            weixinUserToken.setRefreshToken(refreshToken);
            weixinUserToken.setScope(scope);
            weixinUserToken.setUnionId(unionid);

        } catch (JSONException e) {
            LOGGER.error("weixin access token is error", e);
        }

        return weixinUserToken;
    }

    /**
     * 根据code获取accesstoken
     *
     * @return
     */
    public static WeixinUser getWeixinUser(String accessToken, String openId) {

        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId)) {
            return null;
        }

        Map<String ,String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("openid", openId);

        String queryString = generateQueryParam(paramMap);

        String result = RequestUtils.doGet(GET_USER_INFO_URL, queryString);

        WeixinUser weixinUser = new WeixinUser();
        try {
            JSONObject userInfo = new JSONObject(result);
            String nickName = userInfo.getString("nickname");
            int sex = userInfo.getInt("sex");
            String province = userInfo.getString("province");
            String city = userInfo.getString("city");
            String country = userInfo.getString("country");
            String headimgurl = userInfo.getString("headimgurl");
            String privilege = userInfo.getString("privilege");
            String unionid = userInfo.getString("unionid");

            if (StringUtils.isBlank(nickName) || StringUtils.isBlank(headimgurl) || StringUtils.isBlank(unionid)) {
                return null;
            }

            weixinUser.setNickName(nickName);
            weixinUser.setSex(sex);
            weixinUser.setProvince(province);
            weixinUser.setCity(city);
            weixinUser.setCountry(country);
            weixinUser.setHeadImgUrl(headimgurl);
            weixinUser.setPrivilege(privilege);
            weixinUser.setUnionId(unionid);

        } catch (JSONException e) {
            LOGGER.error("weixin access token is error", e);
        }

        return weixinUser;
    }

    private static String generateQueryParam(Map<String, String> paramMap) {
        if (CollectionUtils.isEmpty(paramMap)) {
            return "";
        }

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return URLEncodedUtils.format(params, "UTF-8");
    }

}
