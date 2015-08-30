package com.morningsidevc.wechart.sendmessage;

import java.io.Serializable;

public class WeChartTemplateMessageInfo implements Serializable{

    private static final long serialVersionUID = 1572644314668105777L;

    private String touser;
	private String template_id;
	private String url;
	private String topcolor;
	private WeChartCommentTemplateData data;
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String templateId) {
		template_id = templateId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	public WeChartCommentTemplateData getData() {
		return data;
	}
	public void setData(WeChartCommentTemplateData data) {
		this.data = data;
	}
	
	

}
