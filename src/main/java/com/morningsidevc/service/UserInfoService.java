/**
 * 
 */
package com.morningsidevc.service;

import com.morningsidevc.po.UserInfo;

/**
 * @author yangna
 *
 */
public interface UserInfoService {
	UserInfo loadUserInfo(Integer userId);
	
	void addUserInfo(UserInfo userInfo) throws Exception;
	
	// Integer getFeedCountByUserId(Integer userId);
	
	// Integer getCommentCountByUserId(Integer userId);
}
