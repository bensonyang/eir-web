/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.po.gen.Account;
import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.UserAccountService;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.utils.LoginUtils;
import com.morningsidevc.web.response.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
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

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Resource
    private UserAccountService userAccountService;
    @Resource
    private UserInfoService userInfoService;

    /* HTML */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("reg");

        return mav;
    }

    /* Ajax json */
    @RequestMapping(value = "/ajax/reg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse register(String email, String password, UserInfo userInfo, HttpServletRequest request, HttpServletResponse response) {
        JsonResponse jsonResponse = new JsonResponse();
        try{
            int userId = userAccountService.create(email, password);
            if (userId <= 0) {
                jsonResponse.setCode(400);
                return jsonResponse;
            }
            userInfo.setUserid(userId);
            UserInfo newUser = userInfoService.createUser(userInfo);
            Assert.notNull(newUser);
            LoginUtils.signon(userId, true, request, response);
            jsonResponse.setCode(200);
        }catch (Exception e){
            jsonResponse.setCode(500);
            LOGGER.info("", e);
        }
        return jsonResponse;
    }

    @RequestMapping(value = "/ajax/validate/accountName", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JsonResponse validateAccountName(String email) {
        JsonResponse jsonResponse = new JsonResponse();
        try {
            Account result = userAccountService.loadRyEmail(email);
            if (result == null) {
                jsonResponse.setCode(200);
            }else{
                jsonResponse.setCode(400);
            }
        }catch (Exception e){
            LOGGER.info("", e);
            jsonResponse.setCode(500);
        }
        return jsonResponse;
    }


}
