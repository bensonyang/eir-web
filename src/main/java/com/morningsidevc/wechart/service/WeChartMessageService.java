package com.morningsidevc.wechart.service;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-3, Time: 上午12:08
 */
public interface WeChartMessageService {

    /**
     * 向对应公众号下的用户发送评论模板消息
     *
     * @return
     */
    public boolean sendCommentTemplateMessage(String toOpenId, String toUrl, String fromUserName, String commentTime, String commentContent);
}
