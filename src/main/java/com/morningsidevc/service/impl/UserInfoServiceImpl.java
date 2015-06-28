/**
 * 
 */
package com.morningsidevc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.morningsidevc.dao.gen.UserInfoMapper;
import com.morningsidevc.po.UserInfo;
import com.morningsidevc.service.UserInfoService;

/**
 * @author yangna
 *
 */
@Component
public class UserInfoServiceImpl implements UserInfoService {
	
	@Resource
	private UserInfoMapper userInfoMapper;
	
	/* (non-Javadoc)
	 * @see com.morningsidevc.service.UserInfoService#loadUserInfo(java.lang.Integer)
	 */
	@Override
	public UserInfo loadUserInfo(Integer userId) {
		return userInfoMapper.selectByPrimaryKey(userId);
		
	}

	@Override
	public void addUserInfo(UserInfo userInfo) throws Exception {
		userInfoMapper.insert(userInfo);	
	}

}
