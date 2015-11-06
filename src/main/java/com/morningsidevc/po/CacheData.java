package com.morningsidevc.po;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-10-31, Time: 下午10:00
 */
public class CacheData {

    private Object value;

    private long expiredTime;

    public CacheData(Object value, long expiredTime) {
        this.value = value;
        this.expiredTime = expiredTime;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(long expiredTime) {
        this.expiredTime = expiredTime;
    }
}
