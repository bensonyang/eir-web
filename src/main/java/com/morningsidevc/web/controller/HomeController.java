package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.morningsidevc.po.DescBean;
import com.morningsidevc.service.DescService;
import com.morningsidevc.service.FeedInfoService;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Controller
public class HomeController {

    @Resource
    private DescService descService;
    
    @Resource
    private FeedInfoService feedInfoService;
    
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
	public DescBean load(@PathVariable Integer id) {
		DescBean descBean = this.descService.load(id);
    	return descBean;
	}
    
}
