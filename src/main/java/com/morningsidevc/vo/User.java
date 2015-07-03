/**
 * 
 */
package com.morningsidevc.vo;

/**
 * @author yangna
 *
 */
public class User implements Cloneable{
	private Integer userId;
	private String realName;
	private String jobTitle;
	private String company;
	private String avatarUrl;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public User clone() {
		User user = new User();
		
		user.setAvatarUrl(avatarUrl);
		user.setCompany(company);
		user.setJobTitle(jobTitle);
		user.setRealName(realName);
		user.setUserId(userId);
		return user;
	}
}
