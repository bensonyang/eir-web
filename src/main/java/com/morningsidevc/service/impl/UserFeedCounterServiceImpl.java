/**
 * 
 */
package com.morningsidevc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
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
	
	/* (non-Javadoc)
	 * @see com.morningsidevc.service.UserFeedCounterService#findUserCounter(java.lang.Integer)
	 */
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

}
