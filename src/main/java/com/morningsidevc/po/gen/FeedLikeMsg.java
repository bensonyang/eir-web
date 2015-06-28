package com.morningsidevc.po.gen;

import java.util.Date;

public class FeedLikeMsg {
    private Integer likeid;

    private Integer feedid;

    private Integer feeduserid;

    private Integer userid;

    private Date addtime;

    public Integer getLikeid() {
        return likeid;
    }

    public void setLikeid(Integer likeid) {
        this.likeid = likeid;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}