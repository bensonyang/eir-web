/**
 * 
 */
package com.morningsidevc.web.response;

import java.util.Map;

/**
 * @author yangna
 *
 */
public class JsonResponse {
	protected Integer code;
	protected Object msg;
	
	public JsonResponse() {}
	
	public JsonResponse(Integer code, Object msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	
}
