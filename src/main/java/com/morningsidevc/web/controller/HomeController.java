package com.morningsidevc.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.morningsidevc.service.DescService;
import com.morningsidevc.vo.DescBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author float.lu
 */
@Controller
public class HomeController {

    @Resource
    private DescService descService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public ModelAndView custom() {
    	ModelAndView mav = new ModelAndView();
    	
    	DescBean descBean = this.descService.load(1);
    	mav.addObject("descBean", descBean);
    	mav.setViewName("home");
    	
    	return mav;
    }
    
    @RequestMapping(value = "/ajax", headers = "Accept=application/json")
    public void ajaxHandler(HttpServletResponse response) throws IOException {
    	//表示响应的内容区数据的媒体类型为json格式,且编码为utf-8(客户端应该以utf-8解码) 
    	response.setContentType("application/json;charset=utf-8"); 
    	//写出响应体内容
    	String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
    	response.getWriter().write(jsonData);
    }
    

    
	@RequestMapping(value = "ajax1", method = RequestMethod.GET)
	public void ajax(@ModelAttribute DescBean descBean,PrintWriter printWriter) {
		System.out.println(descBean);
		String jsonString = JSON.toJSONString(descBean, SerializerFeature.PrettyFormat);
		printWriter.write(jsonString);
		printWriter.flush();
		printWriter.close();
	}
    
}
