package com.morningsidevc.wechart.replymessage.xml;


import com.morningsidevc.wechart.enums.ReplyTypeEnum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


/**
 * 回复图文消息
 * Created by shichao.liao on 14-9-4.
 */
@XmlRootElement(name="xml")
public class XmlNews extends XmlMsg {

    // 图文消息个数，限制为10条以内
    private int ArticleCount;

    public XmlNews() {
        MsgType = ReplyTypeEnum.NEWS.getValue();
    }

    // 多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;

    @XmlElement(name="ArticleCount")
    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    @XmlElementWrapper(name="Articles")
    @XmlElement(name="item",type=Article.class)
    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
