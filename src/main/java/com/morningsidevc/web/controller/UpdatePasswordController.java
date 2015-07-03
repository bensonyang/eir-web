/**
 * 
 */
package com.morningsidevc.web.controller;

import com.morningsidevc.service.UserAccountService;
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
public class UpdatePasswordController extends BaseController {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Resource
    private UserAccountService userAccountService;

    /* HTML */
    @RequestMapping(value = "/updatepassword", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("updatepassword");

        return mav;
    }

    /* Ajax json */
    @RequestMapping(value = "/ajax/updatepassword", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public int updatePassword(String oldPassword, String newPassword, HttpServletRequest request, HttpServletResponse response) {
        int userId = getUserId();
        if (userId == 0) {
            return 0;
        }

        int result = userAccountService.updatePassword(userId, oldPassword, newPassword);

        return result;
    }

}
