package com.morningsidevc.crawler;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


/**
 * @author float.lu
 */
public class HTMLCrawlerUtils {

    private static final Logger LOG = LoggerFactory.getLogger("html.crawler.util");

    private static int TIMEOUT = 3000;//超时时间
    private static int KEEPWORDS = 100;//摘要长度
    private static String LAST = "...";
    private static String HTTP = "http://";
    private static String HTTPS = "https://";
    private static int RETRYTIMES = 1;//重试1次，减少用户等待时间（/**重试3次、减少报500概率**/）

    public static HTMLBean get(String url){
        url = adaptUrl(url);
        HTMLBean html = new HTMLBean();
        for(int i=0 ; i < RETRYTIMES; i++){
            try {
                html = doGet(url);
                return html;
            }catch (Exception e){
                LOG.info("", e);
                try {
                    TimeUnit.MICROSECONDS.sleep(500);
                }catch (InterruptedException ie){
                    LOG.info("", e);
                }
            }
        }
        return html;
    }

    private static HTMLBean doGet(String url){
        HTMLBean html = new HTMLBean();
        html.setUrl(url);
        try {
            Document doc = Jsoup.connect(url).timeout(TIMEOUT).get();
            html.setPageTitle(doc.title());
            Element body = doc.body();
            String pageAbstract = fetchAbstract(body);
            if(StringUtils.isNotBlank(pageAbstract)){
                if(StringUtils.length(pageAbstract) > KEEPWORDS){
                    pageAbstract = pageAbstract.substring(0, KEEPWORDS);
                }
                html.setPageAbstract(pageAbstract + LAST);
            }else{
                html.setPageAbstract("");
            }
        }catch (Exception e){
            LOG.info("", e);
            throw new RuntimeException(e);
        }
        return html;
    }

    //貌似豆瓣的网页提取只是提取段落中的文本、所谓我们也只是提取了段落中的文本
    private static String fetchAbstract(Element doc){
        return doc.select("p").text();
    }

    private static String adaptUrl(String url){
        if(StringUtils.startsWith(url, HTTP)){
            return url;
        }else if(StringUtils.startsWith(url, HTTPS)){
            return url;
        }else{
            return HTTP + url;
        }
    }
}
