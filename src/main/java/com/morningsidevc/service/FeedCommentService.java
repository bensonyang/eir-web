package com.morningsidevc.service;

import com.morningsidevc.vo.Comment;

public interface FeedCommentService {
	Comment loadLastestComment(Integer feedId);
}
