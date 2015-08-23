package com.morningsidevc.web.controller;

import com.morningsidevc.utils.LoginUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author float.lu
 */
@Controller
public class MFeedController extends BaseController {

    /* HTML */
    @RequestMapping(value = "/premfeed", method = RequestMethod.GET)
    public  String prefeed(Model model,Integer feedId, Integer userId, HttpServletRequest request, HttpServletResponse response) {
        if(feedId == null) feedId = 95;
        LoginUtils.signon(userId, true, request, response);
        return "redirect:mfeed?feedId=" + feedId;
    }

    /* HTML */
    @RequestMapping(value = "/mfeed", method = RequestMethod.GET)
    public String mfeed(Model model,Integer feedId) {
        if(feedId == null) feedId = 95;
        model.addAttribute("feedId",feedId);
        model.addAttribute("userId",getUserId());
        return "mfeed";
    }

}
