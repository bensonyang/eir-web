package com.morningsidevc.enums;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-29, Time: 上午2:25
 */
public enum WeiXinType {

    WEB("wx512439f72ac23d42", "d98f6f8d669bac1a668927316c2f2135"),

    WECHAT("wx92cb9b591ce7861d", "37bbc3404390eccbd53a466a399b593f");

    private String appid;

    private String secret;

    private WeiXinType(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    public static WeiXinType fromName(String name) {
        for (WeiXinType type : values()) {
            if (type.name().equals(name)) {
                return type;
            }
        }
        return WEB;
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }
}
