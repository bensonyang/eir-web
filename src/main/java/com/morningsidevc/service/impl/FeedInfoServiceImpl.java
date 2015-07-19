/**
 * 
 */
package com.morningsidevc.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.morningsidevc.dao.gen.UserFeedCounterMapper;
import com.morningsidevc.enums.CounterType;
import com.morningsidevc.enums.FeedStatus;
import com.morningsidevc.enums.FeedType;
import com.morningsidevc.enums.MsgType;
import com.morningsidevc.po.FeedCommentCount;
import com.morningsidevc.po.gen.*;
import com.morningsidevc.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.FeedInfoMapper;
import com.morningsidevc.vo.Comment;
import com.morningsidevc.vo.Feed;
import com.morningsidevc.vo.User;
import com.morningsidevc.vo.WebPageMsgBody;
import com.morningsidevc.vo.WeiboMsgBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

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

	@Resource
	private FeedLikeService feedLikeService;

	@Resource
	private UserFeedCounterService userFeedCounterService;

	@Override
	@Transactional(rollbackFor = Throwable.class)
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
		userFeedCounterService.addOneToUserCommentCounter(userId);
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
	public FeedInfo addFeed(Integer userId, String url,String title, String content, String tagName)
			throws Exception {
		if(StringUtils.length(content) > 140){
			throw new RuntimeException(String.format("微博长度不得超过140,当前长度为:" +
					"%s",StringUtils.length(content)));
		}
		WebPageMsg webPageMsg = new WebPageMsg();
		webPageMsg.setTitle(title);
		webPageMsg.setDescription(content);
		webPageMsg.setWebpageurl(url);
		Integer webPageMsgId = webPageMsgService.insertWebPageMsg(webPageMsg);
		if(webPageMsgId == null){
			throw new RuntimeException(String.format("微博内容可能持久化失败，返回ID为:" +
					"%s",webPageMsgId));
		}
		userFeedCounterService.addOneToUserCommentCounter(userId);
		Integer initCommentCount = 0, initLikeCount = 0;
		FeedInfo feedInfo = new FeedInfo();
		feedInfo.setUserid(userId);
		feedInfo.setAddtime(new Date());
		feedInfo.setCommentcount(initCommentCount);
		feedInfo.setLikecount(initLikeCount);
		feedInfo.setLasttime(new Date());
		feedInfo.setTagname(tagName);
		feedInfo.setMsgid(webPageMsgId);
		feedInfo.setStatus(FeedStatus.NORMAL);
		feedInfo.setMsgtype(MsgType.LINK);
		Integer feedId = feedInfoMapper.insert(feedInfo);
		Assert.state(feedId >= 0);
		return feedInfoMapper.selectByPrimaryKey(feedInfo.getFeedid());
	}

	@Override
	public void deleteFeed(Integer feedId) throws Exception {
		FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
		
		if (feedInfo != null) {
			feedInfoMapper.deleteByPrimaryKey(feedId);
			userFeedCounterService.decreaseCounterByOffset(feedInfo.getUserid(), CounterType.FeedCounter.getValue(), 1);
			feedCommentService.deleteCommentOfFeed(feedId, feedInfo.getUserid());
		}
	}

	@Override
	public Boolean isFeedExisted(Integer feedId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void commentIncreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void commentDecreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void likeIncreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void likeDecreasedByOne(Integer feedId) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Feed> findFeeds(int start, int pageSize, Integer currentUserId) throws Exception{
		List<Feed> feedList = new ArrayList<Feed>();
		
		FeedInfoExample feedInfoExample = new FeedInfoExample();
		feedInfoExample.setLimitStart(start);
		feedInfoExample.setLimitEnd(pageSize);
		feedInfoExample.setDistinct(true);
		feedInfoExample.setOrderByClause("FeedId DESC");
		feedInfoExample.createCriteria().andStatusEqualTo(FeedStatus.NORMAL);
		
		/* retrieve feeds */
		List<FeedInfo> feedInfoList = feedInfoMapper.selectByExample(feedInfoExample);
		
		List<Integer> userIdList = new ArrayList<Integer>();
		List<Integer> feedIdList = new ArrayList<Integer>();
		List<Integer> weiboMsgIdList = new ArrayList<Integer>();
		List<Integer> webPageMsgIdList = new ArrayList<Integer>();
		if (feedInfoList != null && feedInfoList.size() != 0) {
			for (FeedInfo feedInfo : feedInfoList) {
				/* retrieve userId list from feedList */
				if (!userIdList.contains(feedInfo.getUserid())) {
					userIdList.add(feedInfo.getUserid());
				}
				
				if (!feedIdList.contains(feedInfo.getFeedid())) {
					feedIdList.add(feedInfo.getFeedid());
				}
				
				if (MsgType.SHUOFEED.equals(feedInfo.getMsgtype())) {
					weiboMsgIdList.add(feedInfo.getMsgid());
				} else if (MsgType.LINK.equals(feedInfo.getMsgtype())) {
					webPageMsgIdList.add(feedInfo.getMsgid());
				}
				
				Feed feed = this.convertFeed(feedInfo);
				
				feedList.add(feed);
			}
			
			/* Map[FeedId]=Object */
			Map<Integer, User> authors = userInfoService.findUsers(userIdList);
			Map<Integer, List<Comment>> comments = feedCommentService.findComments(feedIdList, 2, currentUserId);
			Map<Integer, WeiboMsgBody> weiboMsg = this.weiboMsgService.findMsgBodys(weiboMsgIdList);
			Map<Integer, WebPageMsgBody> webPageMsg = this.webPageMsgService.findMsgBodys(webPageMsgIdList);
			Map<Integer, FeedLikeMsg> feedLikeMsgMap = this.feedLikeService.findIsLiked(feedIdList, currentUserId);
			List<FeedCommentCount> feedCommentCounts = feedCommentService.findFeedCommentsCountByFeedIds(feedIdList);
			Map<Integer,Integer> feedCommentCountMap = mapFeedIdToCommentCount(feedCommentCounts);
			for (Feed element : feedList) {
				//实时计算评论数
				if(feedCommentCountMap.get(element.getFeedId()) != null){
					element.setCommentCount(feedCommentCountMap.get(element.getFeedId()));
				}else {
					element.setCommentCount(0);
				}
				if (authors != null && authors.get(element.getAuthorId()) != null) {
					element.setAuthor(authors.get(element.getAuthorId()).clone());
				}
				if (comments != null && comments.get(element.getFeedId()) != null) {
					element.setComment(comments.get(element.getFeedId()));
					element.setLastCommentIndex(lastCommentIndex(comments.get(element.getFeedId())));
				}else{
					element.setComment(new ArrayList<Comment>());
				}
				if (FeedType.PLAINFEED.getValue().equals(element.getFeedType()) && weiboMsg != null) {
					element.setMsgBody(weiboMsg.get(element.getMsgId()));
				} else if (FeedType.RECLINK.getValue().equals(element.getFeedType()) && webPageMsg != null) {
					element.setMsgBody(webPageMsg.get(element.getMsgId()));
				}
				if(feedLikeMsgMap.get(element.getFeedId()) != null){
					element.setIsLiked(true);
				}else{
					element.setIsLiked(false);
				}
				if(element.getAuthor().getUserId() == currentUserId){
					element.setCanDelete(true);
				}else{
					element.setCanDelete(false);
				}
			}
		} else {
			return null;
		}
		
		return feedList;
	}

	private Map<Integer,Integer> mapFeedIdToCommentCount(List<FeedCommentCount> feedCommentCounts){
		Map<Integer, Integer> result = new HashMap<Integer, Integer>();
		for(FeedCommentCount feedCommentCount : feedCommentCounts){
			result.put(feedCommentCount.getFeedId(), feedCommentCount.getFeedCommentCount());
		}
		return result;
	}

	//取commentId最大的那个，comments的大小有可能是0，1，2
	private Integer lastCommentIndex(List<Comment> comments){
		if(CollectionUtils.isEmpty(comments)) return 0;
		if(comments.size() == 1) return comments.get(0).getCommentId();
		return comments.get(0).getCommentId() < comments.get(1).getCommentId()
				? comments.get(0).getCommentId() : comments.get(1).getCommentId();
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
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = dateFormat.format(feedInfo.getAddtime());
			feed.setAddTime(date);
		}
		
		return feed;
	}

	@Override
	public FeedInfo loadFeedInfo(Integer feedId){
		return feedInfoMapper.selectByPrimaryKey(feedId);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void cutFeedLikeCountByOne(Integer feedId) throws Exception {
		FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
		feedInfo.setLikecount(feedInfo.getLikecount() - 1);
		feedInfoMapper.updateByPrimaryKey(feedInfo);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addFeedLikeCountByOne(Integer feedId) throws Exception {
		FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
		feedInfo.setLikecount(feedInfo.getLikecount() + 1);
		feedInfoMapper.updateByPrimaryKey(feedInfo);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void cutFeedCommentCountByOne(Integer feedId){
		FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
		feedInfo.setCommentcount(feedInfo.getCommentcount() - 1);
		feedInfoMapper.updateByPrimaryKeySelective(feedInfo);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addFeedCommentCountByOne(Integer feedId) throws Exception {
		FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedId);
		feedInfo.setCommentcount(feedInfo.getCommentcount() + 1);
		feedInfoMapper.updateByPrimaryKeySelective(feedInfo);
	}
}
