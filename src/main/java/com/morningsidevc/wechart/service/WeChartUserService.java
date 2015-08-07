package com.morningsidevc.wechart.service;

import com.morningsidevc.wechart.po.WeChartUser;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-3, Time: 上午12:04
 */
public interface WeChartUserService {

    /**
     * 通过HTTP请求从微信服务器端获取对应公众号openId对应的用户基本信息
     *
     * @param openId 公众号下用户的标识
     * @return
     */
    public WeChartUser getUserInfoByOpenId(String openId);
}
