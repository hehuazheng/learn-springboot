package com.hzz.controller;

import com.hzz.model.Tb1;
import com.hzz.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: hezz
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Resource
    private TestService testService;

    @RequestMapping("/sayHi")
    public String sayHi(String name) {
        return "hi, " + name;
    }

    @RequestMapping("/select")
    public Tb1 select(Long id) {
        return testService.selectById(id);
    }

    @RequestMapping("/trac")
    public String testTransaction(String val1, String val2, String rollback) {
        try {
            testService.add(val1, val2, Boolean.valueOf(rollback));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "succ";
    }

    @RequestMapping("/mannualTrac")
    public String testMannualTrac(String val1, String val2, String rollback) {
        try {
            testService.add2(val1, val2, Boolean.valueOf(rollback));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "succ";
    }


}
