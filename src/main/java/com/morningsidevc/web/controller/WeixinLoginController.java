/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.po.WeixinUser;
import com.morningsidevc.service.WeixinUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liang.liu
 *
 */
@Controller
public class WeixinLoginController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private WeixinUserService weixinUserService;

    @RequestMapping(value = "/weixinlogin", method = RequestMethod.GET)
    public String weixinlogin(String code, String state, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isNotBlank(code)) {
            WeixinUser weixinUser = weixinUserService.authWeixinUserInfo(code);
            /*
            if (weixinUserInfo != null) {
                LoginUtils.signon(weixinUserInfo.getUserid(), true, request, response);
            }
            */
        }

        return "redirect:/community";

    }

}
