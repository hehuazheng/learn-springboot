package com.hzz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: hezz
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Value("${test.username}")
    private String testUserName;

    @Value("${test.username2}")
    private String testUserName2;

    @RequestMapping("/sayHi")
    public String sayHi(String name, HttpServletResponse response) {
        LOGGER.debug("调用HelloController.sayHi参数name = {}", name);
        Cookie cookie = new Cookie("token", "hezz");
        response.addCookie(cookie);
        return "hi, " + name + ", " + testUserName + " " + testUserName2;
    }
}
