/**
 * 
 */
package com.morningsidevc.enums;

/**
 * @author yangna
 *
 */
public enum TagStatus {
    NORMAL("NORMAL", Byte.parseByte("0")),
    HIDDEN("HIDDEN", Byte.parseByte("1"));
    
    private String name;
    private Byte value;
    private TagStatus(String name, Byte value) {
    	this.name = name;
    	this.value = value;
    }
    public static String getName(Byte value) {
    	for (TagStatus status : TagStatus.values()) {
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
