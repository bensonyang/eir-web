package com.morningsidevc.web.controller;

import java.util.List;
import java.util.Map;

import com.morningsidevc.po.gen.UserFeedCounter;
import com.morningsidevc.service.TagInfoService;
import com.morningsidevc.service.UserFeedCounterService;
import com.morningsidevc.service.UserInfoService;
import com.morningsidevc.utils.LoginUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.morningsidevc.service.FeedInfoService;
import com.morningsidevc.vo.Tag;
import com.morningsidevc.vo.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author float.lu
 */
@Controller
public class HomeController extends BaseController {
    
    @Resource
    private FeedInfoService feedInfoService;
    
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

	@RequestMapping(value = "/community", method = RequestMethod.POST)
	public ModelAndView signonCommunity(Integer userId, String account,
										HttpServletRequest request, HttpServletResponse response) {
		LoginUtils.signon(userId, account, true, request, response);
		return community(request, response);
	}
    /* Ajax json */
	@RequestMapping(value = "/community/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public User load(@PathVariable Integer id) {
		User user = this.userInfoService.load(id);
    	return user;
	}

}
