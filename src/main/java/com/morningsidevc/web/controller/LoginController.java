/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.service.UserAccountService;
import com.morningsidevc.utils.LoginUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liang.liu
 *
 */
@Controller
public class LoginController extends BaseController {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Resource
    private UserAccountService userAccountService;


    /* Ajax json */
    @RequestMapping(value = "/ajax/login", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int login(String account, String password, HttpServletRequest request, HttpServletResponse response) {
        int result = userAccountService.validate(account, password);

        if (result <= 0) {
            return result;
        }

        LoginUtils.signon(result, account, true, request, response);

        return result;
    }

}
