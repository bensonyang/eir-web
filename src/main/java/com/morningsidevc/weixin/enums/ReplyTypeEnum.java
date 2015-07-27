package com.morningsidevc.weixin.enums;

/**
 * Created by shichao.liao on 14-9-4.
 */
public enum ReplyTypeEnum {

    TEXT("text","回复文本消息"),
    IMAGE("image","回复图像消息"),
    VOICE("voice","回复语音消息"),
    VIDEO("video","回复视频消息"),
    MUSIC("music","回复音乐消息"),
    NEWS("news","回复图文消息");

    private String value;
    private String desc;

    ReplyTypeEnum(String value, String desc){
        this.value=value;
        this.desc=desc;
    }

    public static ReplyTypeEnum getType(String type){

        for(ReplyTypeEnum eventType : ReplyTypeEnum.values()){
            if(eventType.getValue().equalsIgnoreCase(type)){
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
