package com.morningsidevc.wechart.sendmessage;

import java.util.Map;

/**
 * 在发送客服文本消息的时候，
 * 为构造发送给微信服务器的json字符串而设置的数据结构体
 * Created by shichao.liao on 14-9-2.
 */
public class CustomTextMessage {

    private String touser;
    private String msgtype;
    private Map<String,String> text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }
}
