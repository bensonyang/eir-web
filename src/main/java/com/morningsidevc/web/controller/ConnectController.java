package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author float.lu
 */
@Controller
@RequestMapping("connect")
public class ConnectController {

    @RequestMapping("")
    public String index(){
        return "connect";
    }
}
