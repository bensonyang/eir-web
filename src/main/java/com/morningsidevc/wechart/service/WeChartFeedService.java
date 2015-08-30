package com.morningsidevc.wechart.service;

import com.morningsidevc.wechart.replymessage.xml.Article;

import java.util.List;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-8-3, Time: 上午12:08
 */
public interface WeChartFeedService {

    /**
     * 获取最新feed的图文信息
     *
     * @return
     */
    public List<Article> getLastFeedInfo();
}
