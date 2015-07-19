/**
 * 
 */
package com.morningsidevc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.WeiboMsgMapper;
import com.morningsidevc.po.gen.WebPageMsg;
import com.morningsidevc.po.gen.WebPageMsgExample;
import com.morningsidevc.po.gen.WeiboMsg;
import com.morningsidevc.po.gen.WeiboMsgExample;
import com.morningsidevc.service.WeiboMsgService;
import com.morningsidevc.vo.WebPageMsgBody;
import com.morningsidevc.vo.WeiboMsgBody;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangna
 *
 */
@Component
public class WeiboMsgServiceImpl implements WeiboMsgService {
	@Resource
	private WeiboMsgMapper weiboMsgMapper;

	@Override
	public WeiboMsgBody loadMsgBody(Integer msgId) {
		if (msgId == null) {
			return null;
		}
		
		WeiboMsg msg = weiboMsgMapper.selectByPrimaryKey(msgId);
		WeiboMsgBody msgBody = new WeiboMsgBody();
		
		if (msg != null) {
			msgBody.setMsgId(msg.getMsgid());
			msgBody.setContent(msg.getContent());
		} else {
			return null;
		}
		
		return msgBody;
	}

	
	
	@Override
	public Map<Integer, WeiboMsgBody> findMsgBodys(List<Integer> msgIds) {
		if (msgIds == null || msgIds.size() == 0) {
			return null;
		}
		
		Map<Integer, WeiboMsgBody> msgMap = new HashMap<Integer, WeiboMsgBody>();
		WeiboMsgExample example = new WeiboMsgExample();
		example.or().andMsgidIn(msgIds);
		List<WeiboMsg> weiboMsgBodys = weiboMsgMapper.selectByExample(example);
		
		if (weiboMsgBodys != null && weiboMsgBodys.size() != 0) {
			for (WeiboMsg element : weiboMsgBodys) {
				WeiboMsgBody msgBody = new WeiboMsgBody();
				
				msgBody.setMsgId(element.getMsgid());
				msgBody.setContent(element.getContent());
				
				msgMap.put(element.getMsgid(), msgBody);
			}
		} else {
			return null;
		}
		
		return msgMap;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Integer insertWeiboMsg(WeiboMsg weiboMsg) {
		weiboMsgMapper.insert(weiboMsg);
		return weiboMsg.getMsgid();
	}
}
