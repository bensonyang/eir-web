package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.morningsidevc.service.DescService;
import com.morningsidevc.vo.DescBean;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Controller
public class HomeController {

    @Resource
    private DescService descService;
    
    /* HTML */
    @RequestMapping(value = "/community", method = RequestMethod.GET)
    public ModelAndView community() {
    	ModelAndView mav = new ModelAndView();
    	
    	DescBean descBean = this.descService.load(1);
    	mav.addObject("descBean", descBean);
    	mav.setViewName("home");
    	
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
