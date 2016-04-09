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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ua = req.getHeader("User-Agent");
        String forwardPath = req.getRequestURI();
        if (isMobile(ua)) {
            forwardPath = forwardPath.replace("images", "m");
            req.getRequestDispatcher(forwardPath).forward(req, resp);
        } else {
            forwardPath = forwardPath.replace("images", "pc");
            req.getRequestDispatcher(forwardPath).forward(req, resp);
        }
    }


    private boolean isMobile(String ua) {
        if (StringUtils.isEmpty(ua)) {
            return false;
        }
        if (StringUtils.containsIgnoreCase(ua, "iphone") ||
                StringUtils.containsIgnoreCase(ua, "android")) {
            return true;
        }
        return false;
    }
}