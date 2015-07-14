package com.morningsidevc.dao;

import com.morningsidevc.po.FeedCommentCount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author float.lu
 */
public interface FeedCommentDao {
    List<FeedCommentCount> selectFeedCommentCount(@Param("feedIds") List<Integer> feedIds);
}
