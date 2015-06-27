package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.UserInfoMapper;
import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.UserInfoService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    public UserInfo load(int id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
