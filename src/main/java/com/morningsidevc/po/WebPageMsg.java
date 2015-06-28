package com.morningsidevc.po;

public class WebPageMsg {
    private Integer msgid;

    private String title;

    private String description;

    private String webpageurl;

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebpageurl() {
        return webpageurl;
    }

    public void setWebpageurl(String webpageurl) {
        this.webpageurl = webpageurl;
    }
}