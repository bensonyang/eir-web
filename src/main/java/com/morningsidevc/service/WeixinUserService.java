package com.morningsidevc.service;

import com.morningsidevc.po.WeixinUser;
import com.morningsidevc.po.gen.WeixinUserInfo;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-7-12, Time: 下午9:54
 */
public interface WeixinUserService {


    /**
     * 根据authcode验证解析微信用户信息
     *
     * @param authcode
     * @return
     */
    WeixinUser authWeixinUserInfo(String authcode);


    /**
     * 根据userID获取微信用户信息
     *
     * @param userId
     * @return
     */
    WeixinUserInfo getWeixinUserInfoByUserId(int userId);


    /**
     * 根据微信unionId获取微信用户信息
     *
     * @param unionId
     * @return
     */
    WeixinUserInfo getWeixinUserInfoByUnionid(String unionId);



}
