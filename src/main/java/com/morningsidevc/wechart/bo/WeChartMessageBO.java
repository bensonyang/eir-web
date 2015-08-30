package com.morningsidevc.wechart.bo;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.morningsidevc.utils.RequestUtils;
import com.morningsidevc.wechart.sendmessage.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shichao.liao on 14-10-15.
 */
public class WeChartMessageBO {

    private final static Logger logger = LoggerFactory.getLogger(WeChartMessageBO.class);

    private static final String WECHART_MESSAGE_CUSTOM_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

    private static final String WECHART_MESSAGE_TEMPLATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";


    /**
     * 向对应公众号发送客服消息
     *
     * @param accessToken 微信校验凭证
     * @param openId
     * @param text 消息内容
     * @return
     */
    public static boolean sendCustomTextMessage(String accessToken, String openId, String text) {

        logger.info("send Custom Text Message, text=" + text);
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId) || StringUtils.isBlank(text)) {
            return false;
        }

        CustomTextMessage ctm = new CustomTextMessage();
        ctm.setTouser(openId);
        ctm.setMsgtype("text");
        Map<String, String> content = new HashMap<String, String>();
        content.put("content", text);
        ctm.setText(content);
        String message = JSON.toJSONString(ctm);

        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(WECHART_MESSAGE_CUSTOM_URL);
        urlBuffer.append(accessToken);
        String response = RequestUtils.doPost(urlBuffer.toString(), message);

        Gson gson = new Gson();
        BaseResponse baseResponse = gson.fromJson(response, BaseResponse.class);
        logger.info("send Custom Text Message, result:" + response + "; errcode: " + baseResponse.getErrcode() + "; errmsg: " + baseResponse.getErrmsg());
        if (baseResponse == null || !baseResponse.getErrcode().equals("0")) {
            return false;
        }
        return true;

    }

    /**
     * 向对应公众号发送模板消息
     *
     * @param accessToken 微信校验凭证
     * @param text 模板消息数组
     * @return
     */
    public static boolean sendTemplateMessage(String accessToken, String text) {

        logger.info("send template message,text=" + text);
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(text)) {
            return false;
        }

        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(WECHART_MESSAGE_TEMPLATE_URL);
        urlBuffer.append(accessToken);
        String response = RequestUtils.doPost(urlBuffer.toString(), text);

        Gson gson = new Gson();
        BaseResponse baseResponse = gson.fromJson(response, BaseResponse.class);
        logger.info("send template message result:" + response + "; errcode: " + baseResponse.getErrcode() + "; errmsg: " + baseResponse.getErrmsg());
        if (baseResponse == null || !baseResponse.getErrcode().equals("0")) {
            return false;
        }
        return true;
    }

    public static String generateCommentTemplateData(String toOpenId, String toUrl, String fromUserName, String commentTime, String commentContent) {
        WeChartTemplateMessageInfo template=new WeChartTemplateMessageInfo();
        template.setTouser(toOpenId);
        // TODO 确认模版ID
        template.setTemplate_id("rP9MgQ0DJRjGMjfukfBsOH_P8X1ba0RDKkgUR6w7DVY");
        template.setTopcolor("#FF0000");
        template.setUrl(toUrl);
        WeChartCommentTemplateData data=new WeChartCommentTemplateData();

        data.setFirst(new WeChartSendValueInfo("您好，您收到一条评论信息！", "#173177"));
        data.setKeyword1(new WeChartSendValueInfo(fromUserName, "#173177"));
        // SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        data.setKeyword2(new WeChartSendValueInfo(commentTime, "#173177"));
        data.setKeyword3(new WeChartSendValueInfo(commentContent, "#173177"));
        data.setRemark(new WeChartSendValueInfo("点击详情可以查看或评论", "#173177"));

        template.setData(data);

        Gson gson = new Gson();
        return gson.toJson(template);
    }

    /*
    public static void main(String[] args) {

        String text = generateCommentTemplateData("oF633jnVIj8Zg0umJtp-HTuD9B5I", "http://www.msvcplus.com/community", "小鸟", "2016-06-16", "小鸟来此一游！");

        boolean result = sendTemplateMessage("O0KWxUV4hJrqg_z0LE_NRRm96Y7T0T46fydW1-PyWEU6aGjfQtbayWIhHf1iaFRFcalFrdObVNWC34X6NhIqQUj-lPgidPvGjaNoSdLxcsc", text);

        System.out.println("###############");
        System.out.println(result);
    }
    */

}
