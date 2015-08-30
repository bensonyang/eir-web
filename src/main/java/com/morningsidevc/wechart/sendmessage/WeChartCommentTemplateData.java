package com.morningsidevc.wechart.sendmessage;

import java.io.Serializable;

public class WeChartCommentTemplateData implements Serializable{

    private static final long serialVersionUID = -6137822866097090611L;

    private WeChartSendValueInfo title;
	private WeChartSendValueInfo fromUser;
	private WeChartSendValueInfo commentTime;
	private WeChartSendValueInfo commentContent;
	private WeChartSendValueInfo remark;

    public WeChartSendValueInfo getTitle() {
        return title;
    }

    public void setTitle(WeChartSendValueInfo title) {
        this.title = title;
    }

    public WeChartSendValueInfo getFromUser() {
        return fromUser;
    }

    public void setFromUser(WeChartSendValueInfo fromUser) {
        this.fromUser = fromUser;
    }

    public WeChartSendValueInfo getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(WeChartSendValueInfo commentTime) {
        this.commentTime = commentTime;
    }

    public WeChartSendValueInfo getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(WeChartSendValueInfo commentContent) {
        this.commentContent = commentContent;
    }

    public WeChartSendValueInfo getRemark() {
        return remark;
    }

    public void setRemark(WeChartSendValueInfo remark) {
        this.remark = remark;
    }
}
