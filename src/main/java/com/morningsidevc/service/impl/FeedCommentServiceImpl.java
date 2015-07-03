/**
 * 
 */
package com.morningsidevc.service.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.FeedCommentMsgMapper;
import com.morningsidevc.po.gen.FeedCommentMsg;
import com.morningsidevc.po.gen.FeedCommentMsgExample;
import com.morningsidevc.service.FeedCommentService;
import com.morningsidevc.vo.Comment;

/**
 * @author yangna
 *
 */
@Component
public class FeedCommentServiceImpl implements FeedCommentService {
	@Resource
	private FeedCommentMsgMapper feedCommentMsgMapper;
	
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
	public Map<Integer, List<Comment>> findComments(List<Integer> feedIds) {
		if (feedIds == null || feedIds.size() == 0) {
			return null;
		}
		
		Map<Integer, List<Comment>> commentMap = new HashMap<Integer, List<Comment>>();
		FeedCommentMsgExample example = new FeedCommentMsgExample();
		example.setOrderByClause("AddTime DESC");
		example.or().andFeedidIn(feedIds);
		List<FeedCommentMsg> comments = feedCommentMsgMapper.selectByExample(example);
		
		if (comments != null && comments.size() != 0) {
			for (FeedCommentMsg feedCommentMsg : comments) {
				Comment comment = new Comment();
				comment.setCommentId(feedCommentMsg.getCommentid());
				comment.setUserId(feedCommentMsg.getUserid());
				comment.setToUserId(feedCommentMsg.getUserid());
				comment.setContent(feedCommentMsg.getContent());
				
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
		} else {
			return null;
		}
		
		return commentMap;
	}
}
