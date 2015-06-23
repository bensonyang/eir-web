package com.morningsidevc.web.controller;

import com.morningsidevc.service.impl.DescServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author float.lu
 */
@Controller
public class HomeController {

    @Resource
    private DescServiceImpl descService;

    @RequestMapping("/home")
    public String home() {
        return "home";
    }
}
