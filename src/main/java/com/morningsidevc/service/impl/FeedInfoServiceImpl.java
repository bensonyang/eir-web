/**
 * 
 */
package com.morningsidevc.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.morningsidevc.enums.FeedStatus;
import com.morningsidevc.enums.FeedType;
import com.morningsidevc.enums.MsgType;
import com.morningsidevc.po.gen.WeiboMsg;
import org.apache.commons.lang.StringUtils;
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
import com.morningsidevc.vo.MsgBody;
import com.morningsidevc.vo.User;
import com.morningsidevc.vo.WebPageMsgBody;
import com.morningsidevc.vo.WeiboMsgBody;
import org.springframework.util.Assert;

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
	public FeedInfo addFeed(Integer userId, String content, String tagName) throws Exception {
		if(StringUtils.length(content) > 140){
			throw new RuntimeException(String.format("微博长度不得超过140,当前长度为:" +
					"%s",StringUtils.length(content)));
		}
		WeiboMsg weiboMsg = new WeiboMsg();
		weiboMsg.setContent(content);
		Integer weiboMsgId = weiboMsgService.insertWeiboMsg(weiboMsg);
		if(weiboMsgId == null){
			throw new RuntimeException(String.format("微博内容可能持久化失败，返回ID为:" +
					"%s",weiboMsgId));
		}
		Integer initCommentCount = 0, initLikeCount = 0;
		FeedInfo feedInfo = new FeedInfo();
		feedInfo.setUserid(userId);
		feedInfo.setAddtime(new Date());
		feedInfo.setCommentcount(initCommentCount);
		feedInfo.setLikecount(initLikeCount);
		feedInfo.setLasttime(new Date());
		feedInfo.setTagname(tagName);
		feedInfo.setMsgid(weiboMsgId);
		feedInfo.setStatus(FeedStatus.NORMAL);
		feedInfo.setMsgtype(MsgType.SHUOFEED);
		Integer feedId = feedInfoMapper.insert(feedInfo);
		Assert.state(feedId >= 0);
		return feedInfoMapper.selectByPrimaryKey(feedInfo.getFeedid());
	}

	/* (non-Javadoc)
	 * @see com.morningsidevc.service.FeedInfoService#addFeed(java.lang.Integer, java.lang.String, java.lang.String)
	 */
	@Override
	public Integer addFeed(Integer userId, String url, String content, String tagName)
			throws Exception {
		// TODO Auto-generated method stub
		return null;

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
	public List<Feed> findFeeds(int start, int pageSize) throws Exception{
		List<Feed> feedList = new ArrayList<Feed>();
		
		FeedInfoExample feedInfoExample = new FeedInfoExample();
		feedInfoExample.setLimitStart(start);
		feedInfoExample.setLimitEnd(pageSize);
		feedInfoExample.setDistinct(true);
		feedInfoExample.setOrderByClause("FeedId DESC");
		
		List<FeedInfo> feedInfoList = feedInfoMapper.selectByExample(feedInfoExample);
		
		List<Integer> userIdList = new ArrayList<Integer>();
		List<Integer> feedIdList = new ArrayList<Integer>();
		List<Integer> weiboMsgIdList = new ArrayList<Integer>();
		List<Integer> webPageMsgIdList = new ArrayList<Integer>();
		if (feedInfoList != null && feedInfoList.size() != 0) {
			for (FeedInfo feedInfo : feedInfoList) {
				if (!userIdList.contains(feedInfo.getUserid())) {
					userIdList.add(feedInfo.getUserid());
				}
				
				if (!feedIdList.contains(feedInfo.getFeedid())) {
					feedIdList.add(feedInfo.getFeedid());
				}
				
				if (feedInfo.getMsgtype() == 0) {
					weiboMsgIdList.add(feedInfo.getMsgid());
				} else if (feedInfo.getMsgtype() == 1) {
					webPageMsgIdList.add(feedInfo.getMsgid());
				}
				
				Feed feed = this.convertFeed(feedInfo);
				
				feedList.add(feed);
			}
			
			Map<Integer, User> authors = userInfoService.findUsers(userIdList);
			Map<Integer, List<Comment>> comments = feedCommentService.findComments(feedIdList);
			Map<Integer, WeiboMsgBody> weiboMsg = this.weiboMsgService.findMsgBodys(weiboMsgIdList);
			Map<Integer, WebPageMsgBody> webPageMsg = this.webPageMsgService.findMsgBodys(webPageMsgIdList);
			
			for (Feed element : feedList) {
				if (authors != null) {
					element.setAuthor(authors.get(element.getAuthorId()).clone());
				}
				if (comments != null) {
					element.setComment(comments.get(element.getFeedId()));
				}
				if (element.getFeedType() == 0 && weiboMsg != null) {
					element.setMsgBody(weiboMsg.get(element.getMsgId()));
				} else if (element.getFeedType() == 1 && webPageMsg != null) {
					element.setMsgBody(webPageMsg.get(element.getMsgId()));
				}
			}

			
		} else {
			
			return null;
		}
		
		return feedList;
	}
	
	private Feed convertFeed(FeedInfo feedInfo) {
		Feed feed = new Feed();
		
		feed.setFeedId(feedInfo.getFeedid());
		feed.setFeedType((int)feedInfo.getMsgtype());
		feed.setCommentCount(feedInfo.getCommentcount());
		feed.setLikeCount(feedInfo.getLikecount());
		feed.setTag(feedInfo.getTagname());
		feed.setAuthorId(feedInfo.getUserid());
		feed.setMsgId(feedInfo.getMsgid());
		
		if (feedInfo.getAddtime() != null) {
			String date = DateFormat.getDateInstance().format(feedInfo.getAddtime());
			feed.setAddTime(date);
		}
		
		return feed;
	}
}
