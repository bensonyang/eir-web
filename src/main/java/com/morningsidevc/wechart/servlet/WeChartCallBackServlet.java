package com.morningsidevc.wechart.servlet;

import com.morningsidevc.wechart.handler.WeChartMsgHandler;
import com.morningsidevc.wechart.utils.WeChartUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * 大众点评被动消息处理入口
 * 
 * @author shichao.liao
 */
public class WeChartCallBackServlet implements HttpRequestHandler {

    private final static Logger logger = LoggerFactory.getLogger(WeChartCallBackServlet.class);

    private static final String UTF8   = "UTF-8";

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        try {

            preProcessRequest(request);

            if (isIdentityVerifyRequest(request.getMethod())) {
                verifyIdentity(request, response);
            } else {
                processRequest(request, response);
            }

        } catch (Exception e) {
            logger.error("handleRequest Exception!" + getVerifyInfo(request), e);
        }
    }

    private void preProcessRequest(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding(UTF8);
    }

    private void verifyIdentity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echoStr = request.getParameter("echostr");

        if (WeChartUtil.validate(timestamp, nonce, signature, getToken())) {
            response.setStatus(HttpStatus.SC_OK);
            response.getOutputStream().write(echoStr.getBytes());
        } else {
            logger.warn("verifyIdentity: invalidate get request from wechart#" + getVerifyInfo(request));
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PrintWriter out = null;

        WeChartMsgHandler handler = getWeixinMsgHandler();

        try {

            String result = handler.dispose(request);
            if (StringUtils.isBlank(result)) {
                return;
            }

            response.setCharacterEncoding(UTF8);
            out = response.getWriter();
            out.print(result);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private boolean isIdentityVerifyRequest(String method) {
        return "get".equalsIgnoreCase(method);
    }

    private WeChartMsgHandler getWeixinMsgHandler() {
        return new WeChartMsgHandler();
    }

    private String getToken() {
        return "eirweb123";
    }

    private String getVerifyInfo(HttpServletRequest request) {

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echoStr = request.getParameter("echostr");

        StringBuffer baseInfo = new StringBuffer();
        baseInfo.append(" signature=");
        baseInfo.append(signature);
        baseInfo.append(" timestamp=");
        baseInfo.append(timestamp);
        baseInfo.append(" nonce=");
        baseInfo.append(nonce);
        baseInfo.append(" echostr=");
        baseInfo.append(echoStr);

        return baseInfo.toString();
    }
}
