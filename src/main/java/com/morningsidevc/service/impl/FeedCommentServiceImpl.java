/**
 * 
 */
package com.morningsidevc.service.impl;

import java.text.DateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.morningsidevc.dao.gen.FeedInfoMapper;
import com.morningsidevc.dao.gen.UserFeedCounterMapper;
import com.morningsidevc.dao.gen.UserInfoMapper;
import com.morningsidevc.enums.CounterType;
import com.morningsidevc.po.gen.*;
import com.morningsidevc.web.request.AddCommentRequest;
import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.FeedCommentMsgMapper;
import com.morningsidevc.service.FeedCommentService;
import com.morningsidevc.vo.Comment;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

/**
 * @author yangna
 *
 */
@Component
public class FeedCommentServiceImpl implements FeedCommentService {
	@Resource
	private FeedCommentMsgMapper feedCommentMsgMapper;
	@Resource
	private UserFeedCounterMapper userFeedCounterMapper;
	@Resource
	private FeedInfoMapper feedInfoMapper;
	@Resource
	private UserInfoMapper userInfoMapper;
	
	public Comment loadLastestComment(Integer feedId) {
		Comment comment = new Comment();
		FeedCommentMsgExample feedCommentMsgExample = new FeedCommentMsgExample();
		
		feedCommentMsgExample.or().andFeedidEqualTo(feedId);
		feedCommentMsgExample.setOrderByClause("AddTime DESC Limit 1");
		
		List<FeedCommentMsg> resultList = feedCommentMsgMapper.selectByExample(feedCommentMsgExample);
		
		if (resultList != null && resultList.size() != 0) {
			FeedCommentMsg result = resultList.get(0);
			comment.setCommentId(result.getCommentid());
			comment.setUserId(result.getUserid());
			comment.setToUserId(result.getUserid());
			comment.setContent(result.getContent());
			
			if (result.getAddtime() != null) {
				String date = DateFormat.getDateInstance().format(result.getAddtime());
				comment.setCommentTime(date);
			}
			
			return comment;
		}
		return null;
	}

	@Override
	public Map<Integer, List<Comment>> findComments(List<Integer> feedIds) throws Exception{
		if (feedIds == null || feedIds.size() == 0) {
			return null;
		}
		
		Map<Integer, List<Comment>> commentMap = new HashMap<Integer, List<Comment>>();
		FeedCommentMsgExample example = new FeedCommentMsgExample();
		example.setOrderByClause("AddTime DESC");
		example.or().andFeedidIn(feedIds);
		List<FeedCommentMsg> comments = feedCommentMsgMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(comments)){
			List<Integer> userIds = new ArrayList<Integer>(userIds(comments));
			UserInfoExample userInfoExample = new UserInfoExample();
			userInfoExample.createCriteria().andUseridIn(userIds);
			List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
			Map<Integer, UserInfo> userInfoMap = mapUserInfo(userInfos);
			for (FeedCommentMsg feedCommentMsg : comments) {
				Comment comment = new Comment();
				comment.setCommentId(feedCommentMsg.getCommentid());
				comment.setUserId(feedCommentMsg.getUserid());
				comment.setToUserId(feedCommentMsg.getUserid());
				comment.setContent(feedCommentMsg.getContent());
				comment.setUserName(userInfoMap.get(feedCommentMsg.getUserid()).getNickname());
				if(feedCommentMsg.getTouserid() != null){
					comment.setToUserName(userInfoMap.get(feedCommentMsg.getTouserid()).getNickname());
				}

				if (feedCommentMsg.getAddtime() != null) {
					String date = DateFormat.getDateInstance().format(feedCommentMsg.getAddtime());
					comment.setCommentTime(date);
				}

				if (commentMap.containsKey(feedCommentMsg.getFeedid())) {
					commentMap.get(feedCommentMsg.getFeedid()).add(comment);
				} else {
					List<Comment> commentList = new ArrayList<Comment>();
					commentList.add(comment);
					commentMap.put(feedCommentMsg.getFeedid(), commentList);
				}
			}
		}else{
			return null;
		}
		return commentMap;
	}

	private Map<Integer, UserInfo> mapUserInfo(List<UserInfo> userInfos){
		Map<Integer, UserInfo> userInfoMap = new HashMap<Integer, UserInfo>();
		for(UserInfo userInfo : userInfos){
			userInfoMap.put(userInfo.getUserid(), userInfo);
		}
		return userInfoMap;
	}

	private Set<Integer> userIds(List<FeedCommentMsg> comments){
		Set<Integer> userIds = new HashSet<Integer>();
		for(FeedCommentMsg feedCommentMsg : comments){
			userIds.add(feedCommentMsg.getUserid());
			userIds.add(feedCommentMsg.getTouserid());
		}
		return  userIds;
	}
	
	public Comment addComment(AddCommentRequest request, Integer currentUserId) throws Exception{
		FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(request.getFeedId());
		Assert.notNull(feedInfo);
		UserInfo currentUser = userInfoMapper.selectByPrimaryKey(currentUserId);
		Assert.notNull(currentUser);
		UserInfo toUser = userInfoMapper.selectByPrimaryKey(feedInfo.getUserid());
		Assert.notNull(toUser);
		feedInfo.setCommentcount(feedInfo.getCommentcount() + 1);
		feedInfoMapper.updateByPrimaryKeySelective(feedInfo);//更新feed中的评论数
		UserFeedCounterExample example = new UserFeedCounterExample();
		example.createCriteria().andUseridEqualTo(feedInfo.getUserid())
				.andCountertypeEqualTo(CounterType.CommentCounter.getValue());
		List<UserFeedCounter> feedCounters = userFeedCounterMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(feedCounters)){//更新计数器
			UserFeedCounter counter = new UserFeedCounter();
			counter.setSum(1);//初始值
			counter.setCountertype(CounterType.CommentCounter.getValue());
			counter.setUserid(feedInfo.getUserid());
			Integer ret = userFeedCounterMapper.insertSelective(counter);
			Assert.state(ret > 0);
		}else{
			UserFeedCounter counter = feedCounters.get(0);
			counter.setSum(counter.getSum() + 1);
			Integer ret = userFeedCounterMapper.updateByPrimaryKeySelective(counter);
			Assert.state(ret > 0);
		}
		FeedCommentMsg feedCommentMsg = buildNewFeedCommentMsg(feedInfo, request, currentUserId);
		Integer ret = feedCommentMsgMapper.insert(feedCommentMsg);//插入评论内容
		Assert.state(ret > 0);
		Comment comment = new Comment();
		comment.setContent(request.getContent());
		comment.setCommentId(feedCommentMsg.getCommentid());
		comment.setToUserId(feedInfo.getUserid());
		comment.setToUserName(toUser.getNickname());
		comment.setUserId(currentUserId);
		comment.setUserName(currentUser.getNickname());
		
		if (feedCommentMsg.getAddtime() != null) {
			String date = DateFormat.getDateInstance().format(feedCommentMsg.getAddtime());
			comment.setCommentTime(date);
		}
		
		return comment;
	}

	@Override
	public Integer deleteComment(Integer commentId) {
		FeedCommentMsg feedCommentMsg = feedCommentMsgMapper.selectByPrimaryKey(commentId);
		Assert.notNull(feedCommentMsg);
		FeedInfo feedInfo = feedInfoMapper.selectByPrimaryKey(feedCommentMsg.getFeeduserid());
		UserInfo feedUser = userInfoMapper.selectByPrimaryKey(feedInfo.getUserid());
		UserFeedCounterExample example = new UserFeedCounterExample();
		example.createCriteria().andUseridEqualTo(feedUser.getUserid())
				.andCountertypeEqualTo(CounterType.CommentCounter.getValue());
		List<UserFeedCounter> counters = userFeedCounterMapper.selectByExample(example);
		Assert.state(!CollectionUtils.isEmpty(counters));
		UserFeedCounter userFeedCounter = counters.get(0);
		userFeedCounter.setSum(userFeedCounter.getSum() - 1);
		Integer ret = userFeedCounterMapper.updateByPrimaryKeySelective(userFeedCounter);
		Assert.state(ret > 0);
		return feedCommentMsgMapper.deleteByPrimaryKey(commentId);
	}

	private FeedCommentMsg buildNewFeedCommentMsg(FeedInfo feedInfo,
												  AddCommentRequest request, Integer currentUserId){
		FeedCommentMsg feedCommentMsg = new FeedCommentMsg();
		feedCommentMsg.setFeeduserid(feedInfo.getUserid());
		feedCommentMsg.setUserid(currentUserId);
		feedCommentMsg.setFeedid(request.getFeedId());
		feedCommentMsg.setAddtime(new Date());
		feedCommentMsg.setLasttime(new Date());
		feedCommentMsg.setContent(request.getContent());
		feedCommentMsg.setTouserid(request.getToUserId());
		return feedCommentMsg;
	}
}
