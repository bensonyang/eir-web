/**
 * 
 */
package com.morningsidevc.enums;

/**
 * @author yangna
 *
 */
public enum CommentStatus {
    NORMAL("NORMAL", Byte.parseByte("0")),
    DELETED("DELETED", Byte.parseByte("1"));
    
    private String name;
    private Byte value;
    private CommentStatus(String name, Byte value) {
    	this.name = name;
    	this.value = value;
    }
    public static String getName(Byte value) {
    	for (CommentStatus status : CommentStatus.values()) {
    		if (status.value == value) {
    			return status.name;
    		}
    	}
    	return "";
    }
    
    public String getName() {
    	return name;
    }
    
    public Byte getValue() {
    	return value;
    }
}
