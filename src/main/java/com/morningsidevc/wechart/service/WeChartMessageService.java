package com.morningsidevc.wechart.service;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-3, Time: 上午12:08
 */
public interface WeChartMessageService {

    /**
     * 向对应公众号下的用户发送模板消息
     *
     * @param message 信息主体以及收发人等信息都包含其中
     * @return
     */
    public int sendTemplateMessage(String message);
}
