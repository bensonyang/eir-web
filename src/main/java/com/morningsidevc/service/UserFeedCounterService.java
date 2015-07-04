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
	Map<String, Integer> findUserCounter(Integer userId);
}
