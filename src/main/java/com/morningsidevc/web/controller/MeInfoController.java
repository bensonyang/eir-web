package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author float.lu
 */
@Controller
@RequestMapping("meinfo")
public class MeInfoController {

    @RequestMapping("default")
    public String meInfoCenter(){
        return "meinfo";
    }

}
