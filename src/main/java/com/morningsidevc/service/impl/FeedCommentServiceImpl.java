/**
 * 
 */
package com.morningsidevc.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.morningsidevc.dao.FeedCommentDao;
import com.morningsidevc.enums.CommentStatus;
import com.morningsidevc.enums.CounterType;
import com.morningsidevc.po.FeedCommentCount;
import com.morningsidevc.po.gen.*;
import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.web.request.AddCommentRequest;
import com.morningsidevc.web.response.DeleteCommentResponse;
import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.FeedCommentMsgMapper;
import com.morningsidevc.service.FeedCommentService;
import com.morningsidevc.service.UserFeedCounterService;
import com.morningsidevc.vo.Comment;
import org.springframework.transaction.annotation.Transactional;
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
	private FeedCommentDao feedCommentDao;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private FeedInfoService feedInfoService;
	
	@Resource
	private UserFeedCounterService userFeedCounterService;
	
	public Comment loadLastestComment(Integer feedId) {
		Comment comment = new Comment();
		FeedCommentMsgExample feedCommentMsgExample = new FeedCommentMsgExample();
		
		feedCommentMsgExample.createCriteria().andFeedidEqualTo(feedId).andStatusEqualTo(CommentStatus.NORMAL.getValue());
		feedCommentMsgExample.setOrderByClause("AddTime DESC Limit 1");
		
		List<FeedCommentMsg> resultList = feedCommentMsgMapper.selectByExample(feedCommentMsgExample);
		
		if (resultList != null && resultList.size() != 0) {
			FeedCommentMsg result = resultList.get(0);
			comment.setCommentId(result.getCommentid());
			comment.setUserId(result.getUserid());
			comment.setToUserId(result.getUserid());
			comment.setContent(result.getContent());
			
			if (result.getAddtime() != null) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = dateFormat.format(result.getAddtime());
				comment.setCommentTime(date);
			}
			
			return comment;
		}
		return null;
	}

	@Override
	public Map<Integer, List<Comment>> findComments(List<Integer> feedIds, Integer pageSize, Integer currentUserId) throws Exception{
		if (feedIds == null || feedIds.size() == 0) {
			return null;
		}
		
		Map<Integer, List<Comment>> commentMap = new HashMap<Integer, List<Comment>>();
		FeedCommentMsgExample example = new FeedCommentMsgExample();
		example.setOrderByClause("AddTime DESC");
		example.createCriteria().andFeedidIn(feedIds).andStatusEqualTo(CommentStatus.NORMAL.getValue());
		List<FeedCommentMsg> comments = feedCommentMsgMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(comments)){
			List<Integer> userIds = new ArrayList<Integer>(userIds(comments));
			List<UserInfo> userInfos = userInfoService.findUserInfoByIds(userIds);
			Map<Integer, UserInfo> userInfoMap = mapUserInfo(userInfos);
			for (FeedCommentMsg feedCommentMsg : comments) {
				Comment comment = new Comment();
				comment.setCommentId(feedCommentMsg.getCommentid());
				comment.setUserId(feedCommentMsg.getUserid());
				comment.setToUserId(feedCommentMsg.getUserid());
				comment.setContent(feedCommentMsg.getContent());
				comment.setUserName(userInfoMap.get(feedCommentMsg.getUserid()).getRealname());
				comment.setUserPic(userInfoMap.get(feedCommentMsg.getUserid()).getAvatarurl());
				if(comment.getUserId() == currentUserId){
					comment.setCanDelete(true);
				}else{
					comment.setCanDelete(false);
				}
				if(feedCommentMsg.getTouserid() != null){
					comment.setToUserName(userInfoMap.get(feedCommentMsg.getTouserid()).getRealname());
				}

				if (feedCommentMsg.getAddtime() != null) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = dateFormat.format(feedCommentMsg.getAddtime());
					comment.setCommentTime(date);
				}

				//每条Feed默认只取两条评论
				if (commentMap.containsKey(feedCommentMsg.getFeedid())
						&& commentMap.get(feedCommentMsg.getFeedid()).size() < pageSize) {
					commentMap.get(feedCommentMsg.getFeedid()).add(comment);
				} else if(!commentMap.containsKey(feedCommentMsg.getFeedid())) {
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

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Comment addComment(AddCommentRequest request, Integer currentUserId) throws Exception{
		FeedInfo feedInfo = feedInfoService.loadFeedInfo(request.getFeedId());
		UserInfo currentUser = userInfoService.loadUserInfoById(currentUserId);
		UserInfo toUser = userInfoService.loadUserInfoById(feedInfo.getUserid());
		feedInfoService.addFeedCommentCountByOne(request.getFeedId());
		userFeedCounterService.increaseCounterByOffset(currentUserId,CounterType.CommentCounter.getValue(),1);
		FeedCommentMsg feedCommentMsg = buildNewFeedCommentMsg(feedInfo, request, currentUserId);
		Integer ret = feedCommentMsgMapper.insert(feedCommentMsg);//插入评论内容
		Comment comment = new Comment();
		comment.setContent(request.getContent());
		comment.setCommentId(feedCommentMsg.getCommentid());
		if(toUser != null){
			comment.setToUserId(feedInfo.getUserid());
			comment.setToUserName(toUser.getRealname());
		}else{
			comment.setToUserId(0);
			comment.setToUserName("");
		}
		comment.setUserId(currentUserId);
		comment.setUserName(currentUser.getRealname());
		
		if (feedCommentMsg.getAddtime() != null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = dateFormat.format(feedCommentMsg.getAddtime());
			comment.setCommentTime(date);
		}
		
		return comment;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public DeleteCommentResponse deleteComment(Integer commentId) {
		FeedCommentMsg feedCommentMsg = feedCommentMsgMapper.selectByPrimaryKey(commentId);
		FeedInfo feedInfo = feedInfoService.loadFeedInfo(feedCommentMsg.getFeedid());
		feedInfoService.cutFeedCommentCountByOne(feedCommentMsg.getFeedid());
		UserInfo feedUser = userInfoService.loadUserInfoById(feedInfo.getUserid());
		userFeedCounterService.decreaseCounterByOffset(feedUser.getUserid(), CounterType.CommentCounter.getValue(),1);
		feedCommentMsgMapper.deleteByPrimaryKey(commentId);
		DeleteCommentResponse response = new DeleteCommentResponse();
		response.setCommentId(commentId);
		response.setCommentCount(feedInfo.getCommentcount());
		return response;
	}

	@Override
	public List<Comment> moreComment(Integer lastCommentIndex, Integer feedId, Integer pageSize, Integer currentUserId) throws Exception{
		FeedCommentMsgExample example = new FeedCommentMsgExample();
		example.setOrderByClause("AddTime DESC");
		example.setLimitStart(0);
		example.setLimitEnd(pageSize);
		example.createCriteria().andFeedidEqualTo(feedId).andCommentidLessThan(lastCommentIndex).andStatusEqualTo(CommentStatus.NORMAL.getValue());
		List<FeedCommentMsg> comments = feedCommentMsgMapper.selectByExample(example);
		List<Comment> commentList = new LinkedList<Comment>();
		if(!CollectionUtils.isEmpty(comments)){
			List<Integer> userIds = new ArrayList<Integer>(userIds(comments));
			List<UserInfo> userInfos = userInfoService.findUserInfoByIds(userIds);
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
				comment.setToUserPic("");
				comment.setUserPic("");
				if(feedCommentMsg.getUserid() == currentUserId){
					comment.setCanDelete(true);
				}else{
					comment.setCanDelete(false);
				}
				if (feedCommentMsg.getAddtime() != null) {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = dateFormat.format(feedCommentMsg.getAddtime());
					comment.setCommentTime(date);
				}
					commentList.add(comment);
				}
		}
		return commentList;
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
		feedCommentMsg.setStatus(CommentStatus.NORMAL.getValue());
		if(request.getToUserId() != null){
			feedCommentMsg.setTouserid(request.getToUserId());
		}
		return feedCommentMsg;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int deleteCommentOfFeed(Integer feedId, Integer feedUserId) {
		FeedCommentMsgExample example = new FeedCommentMsgExample();
		example.createCriteria().andFeedidEqualTo(feedId);
		
		FeedCommentMsg record = new FeedCommentMsg();
		record.setStatus(CommentStatus.DELETED.getValue());
		int commentCount = feedCommentMsgMapper.updateByExampleSelective(record, example);
		
		return commentCount;
	}

	@Override
	public List<FeedCommentCount> findFeedCommentsCountByFeedIds(List<Integer> feedIds) throws Exception {
		return feedCommentDao.selectFeedCommentCount(feedIds);
	}
}
