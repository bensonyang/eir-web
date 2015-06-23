/**
 * 
 */
package com.morningsidevc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangna
 *
 */
@Controller
public class HelloSeaController {
	@RequestMapping("/hellosea")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "home";
    }
}
