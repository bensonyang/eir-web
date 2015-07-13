package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.WeixinUserInfoMapper;
import com.morningsidevc.po.gen.WeixinUserInfo;
import com.morningsidevc.service.WeixinUserService;

import javax.annotation.Resource;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-7-12, Time: 下午11:18
 */
public class WeixinUserServiceImpl implements WeixinUserService {

    @Resource
    private WeixinUserInfoMapper weixinUserInfoMapper;

    @Override
    public WeixinUserInfo authWeixinUserInfo(String authcode) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WeixinUserInfo getWeixinUserInfoByUserId(int userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public WeixinUserInfo getWeixinUserInfoByUnionid(String unionid) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
