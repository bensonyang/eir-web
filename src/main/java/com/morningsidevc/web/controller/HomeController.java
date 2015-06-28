package com.morningsidevc.web.controller;

import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.morningsidevc.service.FeedInfoService;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Controller
public class HomeController {
    
    @Resource
    private FeedInfoService feedInfoService;
    
	@Resource
	private UserInfoService userInfoService;

    /* HTML */
    @RequestMapping(value = "/community", method = RequestMethod.GET)
    public ModelAndView community() {
    	ModelAndView mav = new ModelAndView();
     	mav.setViewName("home");
    	
     	// UserInfo retrieved from cookie and the number of feed and comment
     	
     	// TagList maybe ajax
     	
     	// FeedList     	
    	return mav;
    }

    /* Ajax json */
	@RequestMapping(value = "/community/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public UserInfo load(@PathVariable Integer id) {
		UserInfo userInfo = this.userInfoService.load(id);
    	return userInfo;
	}

}
