package com.hzz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hezz
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/sayHi")
    public String sayHi(String name) {
        return "hi, " + name;
    }
}
