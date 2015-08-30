package com.morningsidevc.wechart.bo;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.morningsidevc.utils.RequestUtils;
import com.morningsidevc.wechart.sendmessage.BaseResponse;
import com.morningsidevc.wechart.sendmessage.CustomTextMessage;
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

        logger.info("sendTemplateMessage,text=" + text);
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(text)) {
            return false;
        }

        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append(WECHART_MESSAGE_TEMPLATE_URL);
        urlBuffer.append(accessToken);
        String response = RequestUtils.doPost(urlBuffer.toString(), text);

        Gson gson = new Gson();
        BaseResponse baseResponse = gson.fromJson(response, BaseResponse.class);
        logger.info("sendTemplateMessage result:" + response + "; errcode: " + baseResponse.getErrcode() + "; errmsg: " + baseResponse.getErrmsg());
        if (baseResponse == null || !baseResponse.getErrcode().equals("0")) {
            return false;
        }
        return true;
    }

}
