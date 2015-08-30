package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author float.lu
 */
@Controller
public class ConnectController {

    @RequestMapping("connect")
    public String index(){
        return "connect";
    }

    @RequestMapping("connectsuccess")
    public String success(){
        return "connectsuccess";
    }



}
