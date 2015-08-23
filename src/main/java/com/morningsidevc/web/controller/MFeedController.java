package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author float.lu
 */
@Controller
public class MFeedController {
    /* HTML */
    @RequestMapping(value = "/mfeed", method = RequestMethod.GET)
    public String community(Model model,Integer feedId,Integer userId) {
        if(feedId == null) feedId = 95;
        if(userId == null) userId = 22;
        model.addAttribute("feedId",feedId);
        model.addAttribute("userId",userId);
        return "mfeed";
    }

}
