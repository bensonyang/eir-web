package com.morningsidevc.po.gen;

import java.util.Date;

public class FeedInfo {
    private Integer feedid;

    private Integer msgid;

    private Byte msgtype;

    private Integer userid;

    private Byte status;

    private Integer likecount;

    private Integer commentcount;

    private String tagname;

    private Date addtime;

    private Date lasttime;

    public Integer getFeedid() {
        return feedid;
    }

    public void setFeedid(Integer feedid) {
        this.feedid = feedid;
    }

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public Byte getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(Byte msgtype) {
        this.msgtype = msgtype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
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