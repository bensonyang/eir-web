/**
 * 
 */
package com.morningsidevc.enums;

/**
 * @author yangna
 *
 */
public enum AccountStatus {
    NORMAL("NORMAL", Byte.parseByte("0")),
    BLOCKED("BLOCKED", Byte.parseByte("1"));
    
    private String name;
    private Byte value;
    private AccountStatus(String name, Byte value) {
    	this.name = name;
    	this.value = value;
    }
    public static String getName(Byte value) {
    	for (AccountStatus status : AccountStatus.values()) {
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
