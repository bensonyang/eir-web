package com.morningsidevc.weixin.replymessage.util;


import com.morningsidevc.weixin.replymessage.xml.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;


/**
 * 回复消息转换工具类
 * Created by shichao.liao on 14-9-4.
 */
public class MsgConvertUtil {

    /**
     * 将一个回复消息对象转换成可以发送到微信服务器的xml形式
     * @param message
     * @return
     */
    public static String parseMsg2XMLStr(XmlMsg message){
        String result=null;
        Class<? extends XmlMsg> msgClass = null;

       if(message instanceof XmlText) {
           msgClass=XmlText.class;
       }
       else if(message instanceof XmlImage){
           msgClass=XmlImage.class;
       }
       else if(message instanceof XmlVoice){
           msgClass=XmlVoice.class;
       }
       else if(message instanceof XmlVideo){
           msgClass=XmlVideo.class;
       }
       else if(message instanceof XmlMusic){
           msgClass=XmlMusic.class;
       }
       else if(message instanceof XmlNews){
           msgClass=XmlNews.class;
       }
       else {
           return null;
       }

       JAXBContext context;
        try {
            context = JAXBContext.newInstance(msgClass);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            StringWriter writer = new StringWriter();
            mar.marshal(message, writer);
            result=writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
   }
}
