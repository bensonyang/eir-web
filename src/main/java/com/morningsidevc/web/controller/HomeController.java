package com.morningsidevc.web.controller;

import java.util.Map;

import com.morningsidevc.service.TagInfoService;
import com.morningsidevc.service.UserFeedCounterService;
import com.morningsidevc.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.morningsidevc.vo.User;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author float.lu
 */
@Controller
public class HomeController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    
	@Resource
	private UserInfoService userInfoService;
	
	@Resource
	private TagInfoService tagInfoService;
	
	@Resource
	private UserFeedCounterService userFeedCounterService;

    /* HTML */
    @RequestMapping(value = "/community", method = RequestMethod.GET)
    public ModelAndView community(HttpServletRequest request, HttpServletResponse response) {
    	ModelAndView mav = new ModelAndView();
     	mav.setViewName("home");
    	
     	// UserInfo retrieved from cookie and the number of feed and comment
     	int userId = super.getUserId();
     	if (userId != 0) {
     		// 登录
     		User user = userInfoService.load(userId);
     		
     		mav.addObject("loginState", "login");
     		if (user != null) {
     			mav.addObject("loginUser", user);
     		}
     		
     		Map<String, Integer> userFeedCounterMap = userFeedCounterService.findUserCounter(userId);
     		if (!CollectionUtils.isEmpty(userFeedCounterMap)) {
     			mav.addObject("counterMap", userFeedCounterMap);
     		}
     		
     	} else {
     		mav.addObject("loginState", "nologin");
     	}
     	
     	// TagList
     	mav.addObject("tagList", tagInfoService.findTags());
     	
    	return mav;
    }
	
}
