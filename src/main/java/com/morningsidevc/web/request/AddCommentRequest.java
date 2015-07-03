package com.morningsidevc.web.request;

/**
 * @author float.lu
 */
public class AddCommentRequest {

    //动态ID
    private Integer feedId;
    //评论内容
    private String content;
    //给某人评论
    private Integer toUserId;

    public Integer getFeedId() {
        return feedId;
    }

    public void setFeedId(Integer feedId) {
        this.feedId = feedId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }
}
