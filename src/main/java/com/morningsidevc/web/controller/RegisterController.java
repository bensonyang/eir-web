/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.po.gen.Account;
import com.morningsidevc.service.UserAccountService;
import com.morningsidevc.utils.LoginUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liang.liu
 *
 */
@Controller
public class RegisterController extends BaseController {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Resource
    private UserAccountService userAccountService;

    /* HTML */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("register");

        return mav;
    }

    /* Ajax json */
    @RequestMapping(value = "/ajax/reg", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int register(String email, String password, String nickName, String realName, String jobTitle, String company, HttpServletRequest request, HttpServletResponse response) {
        int result = userAccountService.create(email, password);

        if (result <= 0) {
            return result;
        }

        LoginUtils.signon(result, email, true, request, response);

        return result;
    }

    @RequestMapping(value = "/ajax/validate/accountName", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int validateAccountName(String email) {
        Account result = userAccountService.loadRyEmail(email);

        if (result == null) {
            return 0;
        }

        return 1;
    }


}
