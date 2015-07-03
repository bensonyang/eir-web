package com.morningsidevc.service;

import com.morningsidevc.vo.Comment;
import com.morningsidevc.web.request.AddCommentRequest;

public interface FeedCommentService {
	Comment loadLastestComment(Integer feedId);

	/**
	 * 添加评论接口
	 * @param request
	 * @param currentUserId
	 * @return
	 */
	Comment addComment(AddCommentRequest request, Integer currentUserId);
}
