package com.morningsidevc.crawler;

/**
 * @author float.lu
 */
public class HTMLBean {

    private String url;
    private String pageTitle;
    private String pageAbstract;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public String getPageAbstract() {
        return pageAbstract;
    }

    public void setPageAbstract(String pageAbstract) {
        this.pageAbstract = pageAbstract;
    }
}
