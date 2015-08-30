package com.morningsidevc.wechart.sendmessage;

import java.io.Serializable;

public class WeChartSendValueInfo implements Serializable{

    private static final long serialVersionUID = 6865023808910368244L;

    private String value;
	private String color;

    public WeChartSendValueInfo(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

}
