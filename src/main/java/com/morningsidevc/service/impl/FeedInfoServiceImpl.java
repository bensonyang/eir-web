/**
 * 
 */
package com.morningsidevc.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.FeedInfoMapper;
import com.morningsidevc.po.gen.FeedInfo;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.vo.Feed;
import com.morningsidevc.vo.LinkFeed;
import com.morningsidevc.vo.ShuoFeed;

/**
 * @author yangna
 *
 */
@Component
public class FeedInfoServiceImpl implements FeedInfoService {
	
	@Resource
	private FeedInfoMapper feedInfoMapper;
	
	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#addFeed(java.lang.Integer, java.lang.String)
	 */
	@Override
	public void addFeed(Integer userId, String content) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#addFeed(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public void addFeed(Integer userId, String url, String content)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#deleteFeed()
	 */
	@Override
	public void deleteFeed() throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#isFeedExisted(java.lang.Integer)
	 */
	@Override
	public Boolean isFeedExisted(Integer feedId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#commentIncreasedByOne(java.lang.Integer)
	 */
	@Override
	public void commentIncreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#commentDecreasedByOne(java.lang.Integer)
	 */
	@Override
	public void commentDecreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#likeIncreasedByOne(java.lang.Integer)
	 */
	@Override
	public void likeIncreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#likeDecreasedByOne(java.lang.Integer)
	 */
	@Override
	public void likeDecreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Feed> findFeeds() {
		List<Feed> feedList = new ArrayList<Feed>();
		
		List<FeedInfo> feedInfoList = feedInfoMapper.selectByExample(null);
		
		for (FeedInfo feedInfo : feedInfoList) {
			Feed feed = feedInfo.getMsgtype()==0?(new ShuoFeed()):(new LinkFeed());
			
			feedList.add(feed);
		}
		
		return feedList;
	}

}
