package com.morningsidevc.wechart.replymessage.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 发送被动响应消息表头
 * Created by shichao.liao on 14-9-4.
 */
@XmlRootElement(name="xml")
public class XmlMsg {
    
	// 接收方帐号（收到的OpenID）
    protected String ToUserName;
   
    // 开发者微信号
    protected String FromUserName;
    
    // 消息创建时间 （整型）
    protected Long CreateTime;
    
    // 消息类型（text/music/news）
    protected String MsgType;
    
    // 置为1时自动星标
    protected Integer FuncFlag;
	
    @XmlElement(name="ToUserName")
    public String getToUserName() {
		return ToUserName;
	}
    
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return FromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	
	@XmlElement(name="CreateTime")
	public Long getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	@XmlElement(name="FuncFlag")
	public Integer getFuncFlag() {
		return FuncFlag;
	}
	
	public void setFuncFlag(Integer funcFlag) {
		FuncFlag = funcFlag;
	}
}
