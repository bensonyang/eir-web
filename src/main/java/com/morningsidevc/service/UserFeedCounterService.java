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
	
	void decreaseCounterByOffset(Integer userId, Byte counterType, int offset);
	
	void increaseCounterByOffset(Integer userId, Byte counterType, int offset);

	void addOneToUserCommentCounter(Integer userId);

	void cutOneToUserCommentCounter(Integer userId);

	void addOneToUserFeedCounter(Integer userId);

	void cutOneToUserFeedCounter(Integer userId);
}
