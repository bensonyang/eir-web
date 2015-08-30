package com.morningsidevc.wechart.bo;

import com.morningsidevc.enums.WeiXinType;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(OAUTH2_AUTHORIZE_URL);
        urlBuilder.append("?");
        urlBuilder.append("appid=").append(WeiXinType.WECHAT.getAppid());
        urlBuilder.append("&redirect_uri=").append(URLEncoder.encode(WEIXIN_LOGIN_URL + "?channel=" + weiXinType.getChannel() + "&redir=" + URLEncoder.encode(url, "UTF-8"), "UTF-8"));
        urlBuilder.append("&response_type=code&scope=snsapi_userinfo&state=STAT#wechat_redirect");

        return urlBuilder.toString();

    }


}
