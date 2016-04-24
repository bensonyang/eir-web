package com.morningsidevc.servlet;

import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 针对PC和Mobile存储两套图片，做forward，优化移动端体验
 * @author float.lu
 */
public class PicConvertServlet extends HttpServlet {

    private static final String USER_AGENT_HEADER = "User-Agent";
    private static final String ORIGIN_PATH = "images";
    private static final String PC = "pc";
    private static final String M = "m";

    private static final String IPHONE = "iphone";
    private static final String ANDROID = "android";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ua = req.getHeader(USER_AGENT_HEADER);
        String forwardPath = req.getRequestURI();
        if (isMobile(ua)) {
            forwardPath = forwardPath.replace(ORIGIN_PATH, M);
            req.getRequestDispatcher(forwardPath).forward(req, resp);
        } else {
            forwardPath = forwardPath.replace(ORIGIN_PATH, PC);
            req.getRequestDispatcher(forwardPath).forward(req, resp);
        }
    }


    private boolean isMobile(String ua) {
        if (StringUtils.isEmpty(ua)) {
            return false;
        }
        if (StringUtils.containsIgnoreCase(ua, IPHONE) ||
                StringUtils.containsIgnoreCase(ua, ANDROID)) {
            return true;
        }
        return false;
    }
}