package com.morningsidevc.wechart.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * liang.liu
 */
public class WeChartUtil {

    private final static Logger logger = LoggerFactory.getLogger(WeChartUtil.class);

    public static boolean validate(String timestamp, String nonce, String signature, String token) {

        if (StringUtils.isBlank(signature) || StringUtils.isBlank(timestamp) || StringUtils.isBlank(nonce)) {
            return false;
        }

        List<String> list = Lists.newArrayList(timestamp, nonce, token);
        Collections.sort(list);

        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String sha1 = getSha1Str(Joiner.on("").join(list).getBytes());

        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return sha1.equalsIgnoreCase(signature);
    }

    public static String getSha1Str(byte[] byteArray) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("sha-1");
            md.update(byteArray);
            byte[] sha1 = md.digest();
            return byte2hex(sha1);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("getSha1Str NoSuchAlgorithmException", e);
        }
    }

    /**
     * @Description計算二進制數據.<P>
     * @date 2012-05-17
     */
    private static String byte2hex(byte[] b) {// 二行制转字符串
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
        }
        return hs;
    }

    /**
     * 解析微信发来的请求（XML）
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) {

        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = null;
        try {

            inputStream = request.getInputStream();

            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);

            // 得到xml根元素
            Element root = document.getRootElement();

            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList)
                map.put(e.getName(), e.getText());

        } catch (Exception e) {
            logger.error("parseXml error!", e);
        } finally {

            // 释放资源
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("parseXml inputStream close error!", e);
                }
            }
        }

        return map;
    }
}
