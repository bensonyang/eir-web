/**
 * 
 */
package com.morningsidevc.vo;

import java.util.Date;

/**
 * @author yangna
 *
 */
public class Comment {
	private Integer commentId;
	private String userPic;
	private Integer userId;
	private String userName;
	private String toUserPic;
	private Integer toUserId;
	private String toUserName;
	private String content;
	private String commentTime;
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public String getUserPic() {
		return userPic;
	}
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToUserPic() {
		return toUserPic;
	}
	public void setToUserPic(String toUserPic) {
		this.toUserPic = toUserPic;
	}
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	
}
