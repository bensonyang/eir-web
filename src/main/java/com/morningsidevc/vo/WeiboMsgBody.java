/**
 * 
 */
package com.morningsidevc.vo;

/**
 * @author yangna
 *
 */
public class WeiboMsgBody extends MsgBody {
	private Integer msgId;
	private String content;
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
