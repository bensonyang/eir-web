/**
 * 
 */
package com.morningsidevc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.WebPageMsgMapper;
import com.morningsidevc.po.gen.WebPageMsg;
import com.morningsidevc.po.gen.WebPageMsgExample;
import com.morningsidevc.service.WebPageMsgService;
import com.morningsidevc.vo.WebPageMsgBody;

/**
 * @author yangna
 *
 */
@Component
public class WebPageMsgServiceImpl implements WebPageMsgService {
	@Resource
	private WebPageMsgMapper msgMapper;

	@Override
	public WebPageMsgBody loadMsgBody(Integer msgId) {
		WebPageMsg msg = msgMapper.selectByPrimaryKey(msgId);
		WebPageMsgBody msgBody = new WebPageMsgBody();
		
		if (msgBody != null) {
			msgBody.setMsgId(msg.getMsgid());
			msgBody.setTitle(msg.getTitle());
			msgBody.setContent(msg.getDescription());
			msgBody.setLink(msg.getWebpageurl());
		}
		return msgBody;
	}

	@Override
	public Map<Integer, WebPageMsgBody> findMsgBodys(List<Integer> msgIds) {
		if (msgIds == null || msgIds.size() == 0) {
			return null;
		}
		
		Map<Integer, WebPageMsgBody> msgMap = new HashMap<Integer, WebPageMsgBody>();
		WebPageMsgExample example = new WebPageMsgExample();
		example.or().andMsgidIn(msgIds);
		List<WebPageMsg> webPageMsgList = msgMapper.selectByExample(example);
		
		if (webPageMsgList != null && webPageMsgList.size() != 0) {
			for (WebPageMsg element : webPageMsgList) {
				WebPageMsgBody msgBody = new WebPageMsgBody();
				
				msgBody.setMsgId(element.getMsgid());
				msgBody.setTitle(element.getTitle());
				msgBody.setContent(element.getDescription());
				msgBody.setLink(element.getWebpageurl());
				
				msgMap.put(element.getMsgid(), msgBody);
			}
		} else {
			return null;
		}
		
		return msgMap;
	}
	@Override
	public Integer insertWebPageMsg(WebPageMsg webPageMsg) {
		msgMapper.insertSelective(webPageMsg);
		return webPageMsg.getMsgid();
	}
}
