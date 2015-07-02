/**
 * 
 */
package com.morningsidevc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.WeiboMsgMapper;
import com.morningsidevc.po.gen.WeiboMsg;
import com.morningsidevc.service.WeiboMsgService;
import com.morningsidevc.vo.WeiboMsgBody;

/**
 * @author yangna
 *
 */
@Component
public class WeiboMsgServiceImpl implements WeiboMsgService {
	@Resource
	private WeiboMsgMapper weiboMsgMapper;

	@Override
	public WeiboMsgBody loadMsgBody(Integer feedId) {
		if (feedId == null) {
			return null;
		}
		
		WeiboMsg msg = weiboMsgMapper.selectByPrimaryKey(feedId);
		WeiboMsgBody msgBody = new WeiboMsgBody();
		
		if (msg != null) {
			msgBody.setMsgId(msg.getMsgid());
			msgBody.setContent(msg.getContent());
		} else {
			return null;
		}
		
		return msgBody;
	}
	
	
}
