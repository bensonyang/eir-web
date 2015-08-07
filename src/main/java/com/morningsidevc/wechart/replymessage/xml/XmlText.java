package com.morningsidevc.wechart.replymessage.xml;


import com.morningsidevc.wechart.enums.ReplyTypeEnum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 回复文本消息
 * Created by shichao.liao on 14-9-4.
 */
@XmlRootElement(name="xml")
public class XmlText extends XmlMsg {
    
	// 回复的消息内容
    private String Content;
    
    public XmlText() {
    	MsgType = ReplyTypeEnum.TEXT.getValue();
	}
    
	public XmlText(String content) {
		MsgType = ReplyTypeEnum.TEXT.getValue();
		Content = content;
	}

	@XmlElement(name="Content")
	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
