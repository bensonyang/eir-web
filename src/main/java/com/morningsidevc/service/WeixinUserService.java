package com.morningsidevc.service;

import com.morningsidevc.po.gen.WeixinUserInfo;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-7-12, Time: 下午9:54
 */
public interface WeixinUserService {


    WeixinUserInfo authWeixinUserInfo(String authcode);



    WeixinUserInfo getWeixinUserInfoByUserId(int userId);



    WeixinUserInfo getWeixinUserInfoByUnionid(String unionid);



}
