package com.morningsidevc.web.response;

/**
 * @author float.lu
 */
public class FeedLikeResponse {
    private Integer feedId;
    private Integer likeId;

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }
}
