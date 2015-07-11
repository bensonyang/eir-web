package com.morningsidevc.enums;

/**
 * @author float.lu
 */
public enum CounterType {
    
    FeedCounter("FeedCounter", Byte.parseByte("0")),
    CommentCounter("CommentCounter", Byte.parseByte("1"));
    
    private String name;
    private Byte value;
    private CounterType(String name, Byte value) {
    	this.name = name;
    	this.value = value;
    }
    public static String getName(Byte value) {
    	for (CounterType counter : CounterType.values()) {
    		if (counter.value == value) {
    			return counter.name;
    		}
    	}
    	return null;
    }
    
    public String getName() {
    	return name;
    }
    
    public Byte getValue() {
    	return value;
    }
}
