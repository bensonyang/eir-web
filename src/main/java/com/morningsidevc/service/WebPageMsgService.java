package com.morningsidevc.service;

import java.util.List;
import java.util.Map;

import com.morningsidevc.vo.WebPageMsgBody;

public interface WebPageMsgService {
	WebPageMsgBody loadMsgBody(Integer msgId);
	
	Map<Integer, WebPageMsgBody> findMsgBodys(List<Integer> msgIds);
}
