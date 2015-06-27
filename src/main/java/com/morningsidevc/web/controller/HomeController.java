package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.morningsidevc.service.DescService;

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
    	

    	return mav;
    }
<<<<<<< Updated upstream
	
    /* Ajax json */
	@RequestMapping(value = "/community/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DescBean load(@PathVariable Integer id) {
		DescBean descBean = this.descService.load(id);
    	return descBean;
	}
=======
    
    @RequestMapping(value = "/ajax", headers = "Accept=application/json")
    public void ajaxHandler(HttpServletResponse response) throws IOException {
    	//表示响应的内容区数据的媒体类型为json格式,且编码为utf-8(客户端应该以utf-8解码) 
    	response.setContentType("application/json;charset=utf-8"); 
    	//写出响应体内容
    	String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
    	response.getWriter().write(jsonData);
    }
    

/*
	@RequestMapping(value = "ajax1", method = RequestMethod.GET)
	public void ajax(@ModelAttribute DescBean descBean,PrintWriter printWriter) {
		System.out.println(descBean);
		String jsonString = JSON.toJSONString(descBean, SerializerFeature.PrettyFormat);
		printWriter.write(jsonString);
		printWriter.flush();
		printWriter.close();
	}*/
>>>>>>> Stashed changes
    
}
