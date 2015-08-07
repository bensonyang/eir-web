package com.morningsidevc.wechart.replymessage.xml;

import com.morningsidevc.wechart.enums.ReplyTypeEnum;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 回复图片信息
 * Created by shichao.liao on 14-9-4.
 */
@XmlRootElement(name="xml")
public class XmlImage extends XmlMsg {
    private String mediaId;

    public XmlImage(){
        MsgType= ReplyTypeEnum.IMAGE.getValue();
    }

    @XmlElement(name="MediaId")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
