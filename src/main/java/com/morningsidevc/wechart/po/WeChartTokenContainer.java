package com.morningsidevc.wechart.po;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-28, Time: 下午9:26
 */
public class WeChartTokenContainer {

    private String accessToken;

    private long expiresTimestamp;

    public WeChartTokenContainer(String accessToken, long expiresTimestamp) {
        this.accessToken = accessToken;
        this.expiresTimestamp = expiresTimestamp;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresTimestamp() {
        return expiresTimestamp;
    }

    public void setExpiresTimestamp(long expiresTimestamp) {
        this.expiresTimestamp = expiresTimestamp;
    }
}
