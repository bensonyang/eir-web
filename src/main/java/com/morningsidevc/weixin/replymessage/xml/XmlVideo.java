package com.morningsidevc.weixin.replymessage.xml;

import com.morningsidevc.weixin.enums.ReplyTypeEnum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 回复视频消息
 * Created by shichao.liao on 14-9-4.
 */
@XmlRootElement(name="xml")
public class XmlVideo extends XmlMsg {
    private String mediaId;
    private String title;
    private String description;

    public XmlVideo(){
        MsgType = ReplyTypeEnum.VIDEO.getValue();
    }

    @XmlElement(name="MediaId")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @XmlElement(name="Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name="Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
