package com.morningsidevc.web.response;

/**
 * @author float.lu
 */
public class DeleteCommentResponse {
    private Integer commentId;
    private Integer commentCount;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
