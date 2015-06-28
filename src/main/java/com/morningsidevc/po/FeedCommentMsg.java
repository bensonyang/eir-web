package com.morningsidevc.po;

import java.util.Date;

public class FeedCommentMsg {
    private Integer commentid;

    private Integer feedid;

    private Integer feeduserid;

    private String content;

    private Integer userid;

    private Integer touserid;

    private Byte status;

    private Date addtime;

    private Date lasttime;

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getFeedid() {
        return feedid;
    }

    public void setFeedid(Integer feedid) {
        this.feedid = feedid;
    }

    public Integer getFeeduserid() {
        return feeduserid;
    }

    public void setFeeduserid(Integer feeduserid) {
        this.feeduserid = feeduserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTouserid() {
        return touserid;
    }

    public void setTouserid(Integer touserid) {
        this.touserid = touserid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }
}