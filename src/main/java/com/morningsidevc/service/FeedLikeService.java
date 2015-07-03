package com.morningsidevc.service;

public interface FeedLikeService {

    /**
     * 给某个用户的某条Feed点赞
     * @param feedId
     * @return
     */
    Integer addlike(Integer feedId);

    /**
     * 取消点赞
     * @param feedId
     * @return 被删除的feedId
     */
    Integer deletelike(Integer feedId);

}
