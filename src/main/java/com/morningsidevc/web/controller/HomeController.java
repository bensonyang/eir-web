package com.morningsidevc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.morningsidevc.po.gen.UserInfo;
import com.morningsidevc.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author float.lu
 */
@Controller
public class HomeController {

	@Resource
	private UserInfoService userInfoService;

    /* HTML */
    @RequestMapping(value = "/community", method = RequestMethod.GET)
    public ModelAndView community() {
    	ModelAndView mav = new ModelAndView();
    	

    	return mav;
    }

    /* Ajax json */
	@RequestMapping(value = "/community/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public UserInfo load(@PathVariable Integer id) {
		UserInfo userInfo = this.userInfoService.load(id);
    	return userInfo;
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
	public void ajax(@ModelAttribute UserInfo userInfo,PrintWriter printWriter) {
		System.out.println(userInfo);
		String jsonString = JSON.toJSONString(userInfo, SerializerFeature.PrettyFormat);
		printWriter.write(jsonString);
		printWriter.flush();
		printWriter.close();
	}

}
