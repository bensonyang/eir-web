/**
 * 
 */
package com.morningsidevc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.morningsidevc.dao.gen.UserFeedCounterMapper;
import com.morningsidevc.enums.CounterType;
import com.morningsidevc.po.gen.UserFeedCounter;
import com.morningsidevc.po.gen.UserFeedCounterExample;
import com.morningsidevc.service.UserFeedCounterService;

/**
 * @author yangna
 *
 */
@Component
public class UserFeedCounterServiceImpl implements UserFeedCounterService {
	
	@Resource
	private UserFeedCounterMapper mapper;
	
	@Override
	public Map<String, Integer> findUserCounter(Integer userId) {
		
		Map<String, Integer> counterMap = new HashMap<String, Integer>();
		
		UserFeedCounterExample example = new UserFeedCounterExample();
		example.createCriteria().andUseridEqualTo(userId);
		List<UserFeedCounter> counterList = mapper.selectByExample(example);
		
		if (!CollectionUtils.isEmpty(counterList)) {
			for (UserFeedCounter counter : counterList) {
				counterMap.put(CounterType.getName(counter.getCountertype()), counter.getSum());
			}
		}
		return counterMap;
	}
	
	@Override
	public void decreaseCounterByOffset(Integer userId, Byte counterType, int offset) {
		UserFeedCounterExample example = new UserFeedCounterExample();
		example.createCriteria().andUseridEqualTo(userId).andCountertypeEqualTo(counterType);
		
		List<UserFeedCounter> result = mapper.selectByExample(example);
		
		if (result != null && result.size() > 0) {
			Integer sum = result.get(0).getSum();
			result.get(0).setSum(sum - offset);
			
			mapper.updateByPrimaryKeySelective(result.get(0));
		}
		
	}

	@Override
	public void increaseCounterByOffset(Integer userId, Byte counterType, int offset) {
		UserFeedCounterExample example = new UserFeedCounterExample();
		example.createCriteria().andUseridEqualTo(userId).andCountertypeEqualTo(counterType);
		
		List<UserFeedCounter> result = mapper.selectByExample(example);
		
		if (result != null && result.size() > 0) {
			Integer sum = result.get(0).getSum();
			result.get(0).setSum(sum + offset);
			
			mapper.updateByPrimaryKeySelective(result.get(0));
		}
		
	}


	@Override
	public void addOneToUserCommentCounter(Integer userId) {
		UserFeedCounterExample example = new UserFeedCounterExample();
		example.createCriteria().andUseridEqualTo(userId)
				.andCountertypeEqualTo(CounterType.CommentCounter.getValue());
		List<UserFeedCounter> feedCounters = mapper.selectByExample(example);
		if(CollectionUtils.isEmpty(feedCounters)){//更新计数器
			UserFeedCounter counter = new UserFeedCounter();
			counter.setSum(1);//初始值
			counter.setCountertype(CounterType.CommentCounter.getValue());
			counter.setUserid(userId);
			mapper.insertSelective(counter);
		}else{
			UserFeedCounter counter = feedCounters.get(0);
			counter.setSum(counter.getSum() + 1);
			mapper.updateByPrimaryKeySelective(counter);
		}
	}

	@Override
	public void cutOneToUserCommentCounter(Integer userId) {
		UserFeedCounterExample example = new UserFeedCounterExample();
		example.createCriteria().andUseridEqualTo(userId)
				.andCountertypeEqualTo(CounterType.CommentCounter.getValue());
		List<UserFeedCounter> counters = mapper.selectByExample(example);
		Assert.state(!CollectionUtils.isEmpty(counters));
		UserFeedCounter userFeedCounter = counters.get(0);
		userFeedCounter.setSum(userFeedCounter.getSum() - 1);
		mapper.updateByPrimaryKeySelective(userFeedCounter);
	}
}
