package com.hzz.controller;

import com.hzz.service.ConditionService;
import com.hzz.service.ConditionService2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: hezz
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private ConditionService conditionService;

    @Resource
    private ConditionService2 conditionService2;

    @RequestMapping("/sayHi")
    public String sayHi(String name) {
        LOGGER.debug("调用HelloController.sayHi参数name = {}", name);
        return "hi, " + name;
    }

    @RequestMapping("/cond")
    public String condition() {
        return conditionService.getCondition();
    }

    @RequestMapping("/cond2")
    public String condition2() {
        if(conditionService2 == null) {
            return "not found";
        }
        return conditionService2.getCond2();
    }
}
