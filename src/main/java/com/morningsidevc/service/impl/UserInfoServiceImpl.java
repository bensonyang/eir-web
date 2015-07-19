package com.morningsidevc.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.morningsidevc.dao.gen.UserInfoMapper;
import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.po.gen.UserInfoExample;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.vo.User;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    		user.setAvatarUrl("http://i1.dpfile.com/pc/3dc7f5f3ef7468611cb6886fa3010177(120c120)/thumb.jpg");
    	} else {
    		return null;
    	}
    	
        return user;
    }

	@Override
	public Map<Integer, User> findUsers(List<Integer> userIds) {
		if (userIds == null || userIds.size() == 0) {
			return null;
		}
		
		Map<Integer, User> userMap = new HashMap<Integer, User>(); 
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUseridIn(userIds);
		
		List<UserInfo> userInfoList = userInfoMapper.selectByExample(example);
		if (userInfoList != null && userInfoList.size() != 0) {
			for (UserInfo userInfo : userInfoList) {
				User user = new User();
				user.setUserId(userInfo.getUserid());
	    		user.setRealName(userInfo.getRealname());
	    		user.setJobTitle(userInfo.getJobtitle());
	    		user.setCompany(userInfo.getCompany());
	    		user.setAvatarUrl("http://i1.dpfile.com/pc/3dc7f5f3ef7468611cb6886fa3010177(120c120)/thumb.jpg");
				userMap.put(user.getUserId(), user);
			}
		} else {
			return null;
		}
		return userMap;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public UserInfo createUser(UserInfo userInfo) {
		userInfo.setAddtime(new Date());
		userInfo.setLasttime(new Date());
		userInfoMapper.insertSelective(userInfo);
		return userInfo;
	}

	@Override
	public List<UserInfo> findUserInfoByIds(List<Integer> userIds) {
		UserInfoExample userInfoExample = new UserInfoExample();
		userInfoExample.createCriteria().andUseridIn(userIds);
		return userInfoMapper.selectByExample(userInfoExample);
	}

	@Override
	public UserInfo loadUserInfoById(Integer userId) {
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void cutCommentCountByOne(Integer userId) {

	}

	@Override
	public void addCommentCountByOne(Integer userId) {

	}
}
