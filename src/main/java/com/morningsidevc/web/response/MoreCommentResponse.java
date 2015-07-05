package com.morningsidevc.web.response;

import com.morningsidevc.vo.Comment;

import java.util.List;

/**
 * @author float.lu
 */
public class MoreCommentResponse {

    //本次查询最大的CommentID
    private Integer lastCommentIndex;
    //本次返回数
    private Integer size;
    private List<Comment> comments;

    public Integer getLastCommentIndex() {
        return lastCommentIndex;
    }

    public void setLastCommentIndex(Integer lastCommentIndex) {
        this.lastCommentIndex = lastCommentIndex;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
