package com.morningsidevc.utils;

import com.morningsidevc.enums.WeiXinType;
import com.morningsidevc.po.WeixinUser;
import com.morningsidevc.po.WeixinUserToken;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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


    /**
     * 根据code获取accesstoken
     *
     * @return
     */
    public static WeixinUserToken getWeixinUserToken(String authcode, WeiXinType weiXinType) {

        if (StringUtils.isBlank(authcode)) {
            return null;
        }

        Map<String ,String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", weiXinType.getAppid());
        paramMap.put("secret", weiXinType.getSecret());
        paramMap.put("grant_type", "authorization_code");
        paramMap.put("code", authcode);

        String queryString = RequestUtils.generateQueryParam(paramMap);

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
     * 根据accesstoken获取用户信息
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

        String queryString = RequestUtils.generateQueryParam(paramMap);

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

            if (StringUtils.isBlank(nickName) || StringUtils.isBlank(unionid)) {
                return null;
            }

            weixinUser.setOpenId(openId);
            weixinUser.setNickName(nickName);
            weixinUser.setSex(sex);
            weixinUser.setProvince(province);
            weixinUser.setCity(city);
            weixinUser.setCountry(country);
            weixinUser.setHeadImgUrl(headimgurl);
            weixinUser.setPrivilege(privilege);
            weixinUser.setUnionId(unionid);

        } catch (JSONException e) {
            LOGGER.error("weixin user info is error", e);
        }

        return weixinUser;
    }

}
