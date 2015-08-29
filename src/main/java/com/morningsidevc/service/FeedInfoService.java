/**
 * 
 */
package com.morningsidevc.service;

import java.util.List;

import com.morningsidevc.po.gen.FeedInfo;
import com.morningsidevc.vo.Feed;

/**
 * @author yangna
 *
 */
public interface FeedInfoService {
	List<Feed> findFeeds(int start, int pageSize, Integer currentUserID, Integer feedId, String tagName) throws Exception;

	/**
	 * 添加说说接口
	 * @param userId 当前用户ID
	 * @param content 说说内容 140字
	 * @param tagName 标签名
	 * @return FeedId
	 * @throws Exception
	 */
	FeedInfo addFeed(Integer userId, String content, String tagName) throws Exception;

	/**
	 * 添加推荐网页接口
	 * @param userId 当前用户ID
	 * @param url 网页地址
	 * @param content 网页摘要内容
	 * @param tagName 标签名
	 * @return FeedId
	 * @throws Exception
	 */
	FeedInfo addFeed(Integer userId, String url,String title, String content, String tagName) throws Exception;
	
	void deleteFeed(Integer feedId) throws Exception;
	
	Boolean isFeedExisted(Integer feedId);
	
	void commentIncreasedByOne(Integer feedId) throws Exception;
	
	void commentDecreasedByOne(Integer feedId) throws Exception;
	
	void likeIncreasedByOne(Integer feedId) throws Exception;
	
	void likeDecreasedByOne(Integer feedId) throws Exception;

	FeedInfo loadFeedInfo(Integer feedId);

	/**
	 * 指定的FeedLikeCount减1
	 * @param feedId
	 * @throws Exception
	 */
	void cutFeedLikeCountByOne(Integer feedId) throws Exception;

	/**
	 * 指定FeedLikeCount加1
	 * @param feedId
	 * @throws Exception
	 */
	void addFeedLikeCountByOne(Integer feedId) throws Exception;


	/**
	 * 指定FeedCommentCount减1
	 * @param feedId
	 * @throws Exception
	 */
	void cutFeedCommentCountByOne(Integer feedId);

	/**
	 * 指定FeedCommentCount加1
	 * @param feedId
	 * @throws Exception
	 */
	void addFeedCommentCountByOne(Integer feedId) throws Exception;
}
