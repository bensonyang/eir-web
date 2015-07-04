/**
 * 
 */
package com.morningsidevc.service;

import java.util.Map;

/**
 * @author yangna
 *
 */
public interface UserFeedCounterService {
	Map<Integer, Integer> findUserCounter(Integer userId);
}
