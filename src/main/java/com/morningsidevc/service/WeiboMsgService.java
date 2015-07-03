package com.morningsidevc.service;

import java.util.List;
import java.util.Map;

import com.morningsidevc.vo.WeiboMsgBody;

public interface WeiboMsgService {
	WeiboMsgBody loadMsgBody(Integer msgId);
	
	Map<Integer, WeiboMsgBody> findMsgBodys(List<Integer> msgIds);
}
