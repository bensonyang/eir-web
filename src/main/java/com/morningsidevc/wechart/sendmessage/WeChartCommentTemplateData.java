package com.morningsidevc.wechart.sendmessage;

import java.io.Serializable;

public class WeChartCommentTemplateData implements Serializable{

    private static final long serialVersionUID = -6137822866097090611L;

    private WeChartSendValueInfo first;
	private WeChartSendValueInfo keyword1;
	private WeChartSendValueInfo keyword2;
	private WeChartSendValueInfo keyword3;
	private WeChartSendValueInfo remark;

    public WeChartSendValueInfo getFirst() {
        return first;
    }

    public void setFirst(WeChartSendValueInfo first) {
        this.first = first;
    }

    public WeChartSendValueInfo getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(WeChartSendValueInfo keyword1) {
        this.keyword1 = keyword1;
    }

    public WeChartSendValueInfo getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(WeChartSendValueInfo keyword2) {
        this.keyword2 = keyword2;
    }

    public WeChartSendValueInfo getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(WeChartSendValueInfo keyword3) {
        this.keyword3 = keyword3;
    }

    public WeChartSendValueInfo getRemark() {
        return remark;
    }

    public void setRemark(WeChartSendValueInfo remark) {
        this.remark = remark;
    }
}
