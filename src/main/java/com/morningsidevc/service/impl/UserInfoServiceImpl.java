package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.UserInfoMapper;
import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.vo.User;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    
    public User load(int id) {
    	UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
    	User user = new User();
    	
    	if (userInfo != null) {
    		user.setUserId(userInfo.getUserid());
    		user.setRealName(userInfo.getRealname());
    		user.setJobTitle(userInfo.getJobtitle());
    		user.setCompany(userInfo.getCompany());
    	} else {
    		return null;
    	}
    	
        return user;
    }
}
