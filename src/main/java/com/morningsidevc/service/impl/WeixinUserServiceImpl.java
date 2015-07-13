package com.morningsidevc.service.impl;

import com.morningsidevc.dao.gen.WeixinUserInfoMapper;
import com.morningsidevc.po.WeixinUser;
import com.morningsidevc.po.WeixinUserToken;
import com.morningsidevc.po.gen.WeixinUserInfo;
import com.morningsidevc.po.gen.WeixinUserInfoExample;
import com.morningsidevc.service.UserAccountService;
import com.morningsidevc.service.WeixinUserService;
import com.morningsidevc.utils.WeixinOAuthClient;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-7-12, Time: 下午11:18
 */
@Component
public class WeixinUserServiceImpl implements WeixinUserService {
    private final static Logger LOGGER = Logger.getLogger(WeixinUserServiceImpl.class);

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private WeixinUserInfoMapper weixinUserInfoMapper;

    @Override
    public WeixinUserInfo authWeixinUserInfo(String authcode) {
        if (StringUtils.isBlank(authcode)) {
            return null;
        }

        WeixinUserToken weixinUserToken = WeixinOAuthClient.getWeixinUserToken(authcode);
        if (weixinUserToken == null) {
            LOGGER.warn("weixinUserToken is null!");
            return null;
        }

        WeixinUser weixinUser = WeixinOAuthClient.getWeixinUser(weixinUserToken.getAccessToken(), weixinUserToken.getOpenId());
        if (weixinUser == null) {
            LOGGER.warn("weixinUser is null!");
            return null;
        }

        WeixinUserInfo weixinUserInfo = new WeixinUserInfo();

        WeixinUserInfo result = getWeixinUserInfoByUnionid(weixinUser.getUnionId());
        if (result == null) {
            int userId = userAccountService.create(String.valueOf(new Date().getTime()) + weixinUserToken.getUnionId(), weixinUserToken.getUnionId());
            convertWeixinUserInfo(weixinUser, weixinUserInfo);
            weixinUserInfo.setUserid(userId);
            weixinUserInfo.setOpenid(weixinUserToken.getOpenId());
            weixinUserInfoMapper.insertSelective(weixinUserInfo);
        } else {
            convertWeixinUserInfo(weixinUser, weixinUserInfo);
            weixinUserInfo.setId(result.getId());
            weixinUserInfo.setUserid(result.getUserid());
            weixinUserInfo.setOpenid(weixinUserToken.getOpenId());
            weixinUserInfoMapper.updateByPrimaryKeySelective(weixinUserInfo);
        }

        return weixinUserInfo;
    }

    @Override
    public WeixinUserInfo getWeixinUserInfoByUserId(int userId) {
        if (userId <= 0) {
            return null;
        }

        WeixinUserInfoExample example = new WeixinUserInfoExample();
        example.createCriteria().andUseridEqualTo(userId);
        List<WeixinUserInfo> result = weixinUserInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public WeixinUserInfo getWeixinUserInfoByUnionid(String unionId) {
        if (StringUtils.isBlank(unionId)) {
            return null;
        }

        WeixinUserInfoExample example = new WeixinUserInfoExample();
        example.createCriteria().andUnionidEqualTo(unionId);
        List<WeixinUserInfo> result = weixinUserInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(result)) {
            return null;
        }
        return result.get(0);

    }

    private void convertWeixinUserInfo(WeixinUser weixinUser, WeixinUserInfo weixinUserInfo) {
        weixinUserInfo.setAvatarurl(weixinUser.getHeadImgUrl());
        weixinUserInfo.setWeixinusername(weixinUser.getNickName());
        weixinUserInfo.setGender((byte) weixinUser.getSex());
        weixinUserInfo.setProvince(weixinUser.getProvince());
        weixinUserInfo.setCity(weixinUser.getCity());
        weixinUserInfo.setCountry(weixinUser.getCountry());
        weixinUserInfo.setPrivilege(weixinUser.getPrivilege());
        weixinUserInfo.setUnionid(weixinUserInfo.getUnionid());
    }
}
