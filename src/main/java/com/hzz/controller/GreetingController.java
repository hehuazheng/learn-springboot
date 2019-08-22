package com.hzz.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: hezz
 */
@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @GetMapping
    public ModelAndView list(Model model) {
        return new ModelAndView("greeting/list", "model", model);
    }
}
