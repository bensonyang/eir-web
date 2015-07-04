package com.morningsidevc.service;

public interface FeedLikeService {

    /**
     * 给某个用户的某条Feed点赞
     * @param feedId
     * @return
     */
    Integer addlike(Integer feedId) throws Exception;

    /**
     * 取消点赞
     * @param feedId
     * @param currentUserId
     * @return 被删除的feedId
     */
    Integer deletelike(Integer feedId, Integer currentUserId) throws Exception;

}
