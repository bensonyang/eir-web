/**
 * 
 */
package com.morningsidevc.vo;

/**
 * @author yangna
 *
 */
public class WebPageMsgBody extends MsgBody {
	private Integer msgId;
	private String title;
	private String content;
	private String link;
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
}
