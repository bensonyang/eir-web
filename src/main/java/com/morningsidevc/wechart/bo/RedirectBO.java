package com.morningsidevc.wechart.bo;

import com.morningsidevc.enums.WeiXinType;
import com.morningsidevc.utils.RequestUtils;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shichao.liao on 14-9-27.
 */
public class RedirectBO {

    private final static String OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize";

    private final static String WEIXIN_LOGIN_URL = "http://www.msvcplus.com/weixinlogin";


    public static String generateUserAuthorizeUrl(String url, WeiXinType weiXinType) throws UnsupportedEncodingException {

        if (StringUtils.isBlank(url)) {
            return "";
        }

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", WeiXinType.WECHAT.getAppid());
        paramMap.put("redirect_uri", URLEncoder.encode(WEIXIN_LOGIN_URL + "?weiXinType=" + weiXinType.name() + "redir=" + URLEncoder.encode(url, "UTF-8"), "UTF-8"));
        paramMap.put("response_type", "code");
        paramMap.put("scope", "snsapi_userinfo");
        paramMap.put("state", "STATE");
        String queryString = RequestUtils.generateQueryParam(paramMap);

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(OAUTH2_AUTHORIZE_URL);
        urlBuilder.append("?");
        urlBuilder.append(queryString);
        urlBuilder.append("#wechat_redirect");

        return urlBuilder.toString();

    }


}
