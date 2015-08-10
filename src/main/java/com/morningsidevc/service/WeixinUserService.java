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

    /**
     * 添加微信用户信息
     *
     * @param weixinUserInfo
     */
    void insertWeixinUserInfo(WeixinUserInfo weixinUserInfo);

    /**
     * 更新微信用户信息
     *
     * @param weixinUserInfo
     */
    void updateWeixinUserInfo(WeixinUserInfo weixinUserInfo);


    /**
     * 更新微信用户标示映射信息
     *
     * @param unionid
     * @param openid
     * @param channel
     */
    void updateWeixinUserMapping(String unionid, String openid, Byte channel);


}
