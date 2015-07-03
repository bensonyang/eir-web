package com.morningsidevc.service;

import java.util.List;
import java.util.Map;

import com.morningsidevc.vo.Comment;

public interface FeedCommentService {
	Comment loadLastestComment(Integer feedId);
	
	Map<Integer, List<Comment>> findComments(List<Integer> feedIds);
}
