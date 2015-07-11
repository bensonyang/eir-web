package com.morningsidevc.service;

import com.morningsidevc.po.gen.FeedLikeMsg;

import java.util.*;

public interface FeedLikeService {

    /**
     * 给某个用户的某条Feed点赞
     * @param feedId
     * @param currentUserId
     * @return
     */
    Integer addlike(Integer feedId, Integer currentUserId) throws Exception;

    /**
     * 取消点赞
     * @param feedId
     * @return 被删除的feedId
     */
    Integer deletelike(Integer feedId, Integer currentUserId) throws Exception;

    /**
     * 批量查询指定用户对Feeds的点赞情况
     * @param feedIds
     * @return
     * @throws Exception
     */
    Map<Integer, FeedLikeMsg> findIsLiked(List<Integer> feedIds, Integer currentUserId) throws Exception;


    /**
     * 统计某个Feed的点赞数
     * @param feedId
     * @param currentUserId
     * @return
     */
    Integer countFeedCount(Integer feedId, Integer currentUserId);

}
