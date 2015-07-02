/**
 * 
 */
package com.morningsidevc.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.FeedInfoMapper;
import com.morningsidevc.po.gen.FeedInfo;
import com.morningsidevc.po.gen.FeedInfoExample;
import com.morningsidevc.service.FeedCommentService;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.service.WebPageMsgService;
import com.morningsidevc.service.WeiboMsgService;
import com.morningsidevc.vo.Comment;
import com.morningsidevc.vo.Feed;
import com.morningsidevc.vo.LinkFeed;
import com.morningsidevc.vo.WebPageMsgBody;
import com.morningsidevc.vo.ShuoFeed;
import com.morningsidevc.vo.User;
import com.morningsidevc.vo.WeiboMsgBody;

/**
 * @author yangna
 *
 */
@Component
public class FeedInfoServiceImpl implements FeedInfoService {
	
	@Resource
	private FeedInfoMapper feedInfoMapper;
	
	@Resource
	private FeedCommentService feedCommentService;
	
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private WebPageMsgService webPageMsgService;
	
	@Resource
	private WeiboMsgService weiboMsgService;
	
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
	public List<Feed> findFeeds(int start, int pageSize) {
		List<Feed> feedList = new ArrayList<Feed>();
		
		FeedInfoExample feedInfoExample = new FeedInfoExample();
		feedInfoExample.setLimitStart(start+1);
		feedInfoExample.setLimitEnd(start+pageSize);
		feedInfoExample.setDistinct(true);
		feedInfoExample.setOrderByClause("FeedId DESC");

		List<FeedInfo> feedInfoList = feedInfoMapper.selectByExample(feedInfoExample);
		
		if (feedInfoList!=null && feedInfoList.size()!=0) {
			for (FeedInfo feedInfo : feedInfoList) {
				User author = userInfoService.load(feedInfo.getUserid());
				Comment comment = feedCommentService.loadLastestComment(feedInfo.getFeedid());
				
				if (feedInfo.getMsgtype()==0) {
					ShuoFeed feed = new ShuoFeed();
					this.convertFeed(feed, feedInfo);
					WeiboMsgBody msgBody = this.weiboMsgService.loadMsgBody(feedInfo.getFeedid());
					
					feed.setAuthor(author);
					feed.setMsgBody(msgBody);
					feed.setComment(comment);
					
					feedList.add(feed);
					
				} else {
					LinkFeed feed = new LinkFeed();
					this.convertFeed(feed, feedInfo);
					
					WebPageMsgBody msgBody = this.webPageMsgService.loadMsgBody(feedInfo.getMsgid());
					
					feed.setAuthor(author);
					feed.setMsgBody(msgBody);
					feed.setComment(comment);
					
					feedList.add(feed);
				}
				
				
			}
		} else {
			return null;
		}
		
		return feedList;
	}
	
	private void convertFeed(Feed feed, FeedInfo feedInfo) {
		feed.setFeedId(feedInfo.getFeedid());
		feed.setFeedType((int)feedInfo.getMsgtype());
		feed.setCommentCount(feedInfo.getCommentcount());
		feed.setLikeCount(feedInfo.getLikecount());
		feed.setTag(feedInfo.getTagname());
		
		if (feedInfo.getAddtime() != null) {
			String date = DateFormat.getDateInstance().format(feedInfo.getAddtime());
			feed.setAddTime(date);
		}
	}
}
