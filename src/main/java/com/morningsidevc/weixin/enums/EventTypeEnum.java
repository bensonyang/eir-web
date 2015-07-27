package com.morningsidevc.weixin.enums;

/**
 * Created by shichao.liao on 14-9-5.
 */
public enum EventTypeEnum {

    SUBSCRIBE("subscribe", "订阅"),
    UN_SUBSCRIBE("unsubscribe", "取消订阅"),
    SCAN("scan", "扫描二维码"),
    LOCATION("location", "发送地理位置"),
    CLICK("click", "点击菜单"),
    VIEW("view", "外链跳转");

    private String value;
    private String desc;

    EventTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static EventTypeEnum getType(String type) {

        for (EventTypeEnum eventType : EventTypeEnum.values()) {
            if (eventType.getValue().equalsIgnoreCase(type)) {
                return eventType;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
