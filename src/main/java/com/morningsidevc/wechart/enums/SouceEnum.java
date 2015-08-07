package com.morningsidevc.wechart.enums;

/**
 * 关注来源
 * 
 * @author jinming.wu
 * @date 2014-9-25
 */
public enum SouceEnum {

    /** 扫码 */
    SCAN(1),

    /** 普通 */
    NORMAL(0);

    public int code;

    SouceEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
