package com.morningsidevc.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: liang.liu
 * <p/>
 * Date: 15-7-12, Time: 下午11:25
 */
public class RequestUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestUtils.class);


    public static String doGet(String url, String queryString) {

        if (StringUtils.isNotBlank(queryString)) {
            String queryDelim = url.contains("?") ? "&" : "?";
            StringBuilder sb = new StringBuilder(url).append(queryDelim).append(queryString);
            url = sb.toString();
        }

        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.connect();
            String result = getResult(con.getInputStream(), "UTF-8");
            return result;
        } catch (Exception e) {
            LOGGER.error("Do get request error!", e);
            return "";
        } finally {
            if (con != null) {
                con.disconnect();
            }
            con = null;
        }
    }

    /**
     * oauth 2.0 doPost
     *
     * @param url
     * @param queryString
     * @return
     */
    public static String doPost(String url, String queryString) {
        HttpURLConnection con = null;
        OutputStream osw = null;
        try {

            con = (HttpURLConnection) new URL(url).openConnection();
            con.setDoInput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            byte[] bytes = queryString.getBytes("UTF-8");
            con.setRequestProperty("Content-Length", Integer.toString(bytes.length));
            osw = con.getOutputStream();
            osw.write(bytes);
            osw.flush();
            osw.close();
            return getResult(con.getInputStream(), "UTF-8");
        } catch (Exception e) {
            LOGGER.error("Do post request error!", e);
            return "";
        } finally {
            if (con != null) {
                con.disconnect();
            }
            con = null;
            osw = null;
        }

    }

    /**
     * 获取返回值
     *
     * @param content
     * @param encode
     * @return
     * @throws java.io.IOException
     */
    private static String getResult(InputStream content, String encode) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(content, encode));
        String line = reader.readLine();
        try {
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            throw new IOException();
        } finally {
            content = null;
            if (reader != null) {
                reader.close();
            }
            reader = null;
        }
        return sb.toString();
    }


}
