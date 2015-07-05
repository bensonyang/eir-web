package com.morningsidevc.service;

import java.util.List;
import java.util.Map;

import com.morningsidevc.vo.Comment;
import com.morningsidevc.web.request.AddCommentRequest;

public interface FeedCommentService {
	Comment loadLastestComment(Integer feedId);
	
	Map<Integer, List<Comment>> findComments(List<Integer> feedIds, Integer pageSize) throws Exception;

	/**
	 * 添加评论接口
	 * @param request
	 * @param currentUserId
	 * @return
	 */
	Comment addComment(AddCommentRequest request, Integer currentUserId) throws Exception;


	/**
	 * 删除评论接口
	 * @param commentId
	 * @return
	 */
	Integer deleteComment(Integer commentId);

	/**
	 * 获取更多评论
	 * @param lastCommentIndex
	 * @param feedId
	 * @param pageSize
	 * @return
	 */
	List<Comment> moreComment(Integer lastCommentIndex, Integer feedId, Integer pageSize) throws Exception;
}
