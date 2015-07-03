/**
 * 
 */
package com.morningsidevc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.WebPageMsgMapper;
import com.morningsidevc.po.gen.WebPageMsg;
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
	
	
}
