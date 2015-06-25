package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.morningsidevc.service.DescService;

import javax.annotation.Resource;

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
}
