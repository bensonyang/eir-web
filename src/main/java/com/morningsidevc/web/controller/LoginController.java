/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.service.UserAccountService;
import com.morningsidevc.utils.LoginUtils;
import com.morningsidevc.web.response.JsonResponse;
import com.morningsidevc.web.response.LoginResponse;
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
    public JsonResponse login(String account, String password, HttpServletRequest request, HttpServletResponse response) {
        int result = userAccountService.validate(account, password);
        JsonResponse jsonResponse = new JsonResponse();
        if (result <= 0) {
            jsonResponse.setCode(400);
            return jsonResponse;
        }
        LoginUtils.signon(result, true, request, response);
        jsonResponse.setCode(200);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(result);
        loginResponse.setAccount(account);
        jsonResponse.setMsg(loginResponse);
        return jsonResponse;
    }

    /* Ajax json */
    @RequestMapping(value = "/ajax/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse logout(HttpServletRequest request, HttpServletResponse response) {
        LoginUtils.signout(request, response);
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(200);
        return jsonResponse;
    }

}
