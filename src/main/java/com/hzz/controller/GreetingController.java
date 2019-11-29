package com.hzz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: hezz
 */
@Controller
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping
    public String list(Model model) {
        model.addAttribute("name", "hezz");
        return "greeting/list";
    }
}
