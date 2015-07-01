package com.morningsidevc.enums;

/**
 * 动态类型枚举
 * @author float.lu
 */
public enum FeedType {
    PLAINFEED(0), //短文
    RECLINK(1); //推荐网址
    private int value;
    private FeedType(int value){
        this.value = value;
    }
    public static FeedType fromInt(int value){
        switch(value){
            case 0: return PLAINFEED;
            case 1: return RECLINK;
        }
        throw new RuntimeException(String.format("convert from int{%d} to FeedType Failed" +
                ", please check your int value.", value));
    }
}