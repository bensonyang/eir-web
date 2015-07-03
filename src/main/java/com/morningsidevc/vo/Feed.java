/**
 * 
 */
package com.morningsidevc.vo;



/**
 * @author yangna
 *
 */
public class Feed {
	private Integer feedId;
	private Integer feedType;
	private String addTime;
	private Integer commentCount;
	private Integer likeCount;
	private String tag;
	
	private User author;
	private MsgBody msgBody;
	private Comment comment;
	
	public Integer getFeedId() {
		return feedId;
	}
	public void setFeedId(Integer feedId) {
		this.feedId = feedId;
	}
	public Integer getFeedType() {
		return feedType;
	}
	public void setFeedType(Integer feedType) {
		this.feedType = feedType;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public MsgBody getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(MsgBody msgBody) {
		this.msgBody = msgBody;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
}
