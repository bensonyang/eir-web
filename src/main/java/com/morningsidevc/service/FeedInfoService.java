/**
 * 
 */
package com.morningsidevc.service;

import java.util.List;

import com.morningsidevc.vo.Feed;

/**
 * @author yangna
 *
 */
public interface FeedInfoService {
	List<Feed> findFeeds(int start, int pageSize);
	
	void addFeed(Integer userId, String content) throws Exception;
	
	void addFeed(Integer userId, String url, String content) throws Exception;
	
	void deleteFeed() throws Exception;
	
	Boolean isFeedExisted(Integer feedId);
	
	void commentIncreasedByOne(Integer feedId) throws Exception;
	
	void commentDecreasedByOne(Integer feedId) throws Exception;
	
	void likeIncreasedByOne(Integer feedId) throws Exception;
	
	void likeDecreasedByOne(Integer feedId) throws Exception;
}
