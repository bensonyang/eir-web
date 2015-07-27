package com.morningsidevc.weixin.enums;

/**
 * Created by liang.liu on 15-7-5.
 */
public enum MsgTypeEnum {

    TEXT("text", "文本消息"),
    IMAGE("image", "图像消息"),
    VOICE("voice", "语音消息"),
    VIDEO("video", "视频消息"),
    LOCATION("location", "地理位置消息"),
    LINK("link", "链接消息"),
    EVENT("event", "事件"),
    REQUEST("request", "收到投诉"),
    CONFIRM("confirm", "受理投诉"),
    REJECT("reject", "拒绝投诉");

    private String value;
    private String desc;

    MsgTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static MsgTypeEnum getType(String type) {

        for (MsgTypeEnum mst : MsgTypeEnum.values()) {
            if (mst.getValue().equalsIgnoreCase(type)) {
                return mst;
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
