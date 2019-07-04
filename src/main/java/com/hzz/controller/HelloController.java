package com.hzz.controller;

import com.hzz.mapper.Tb1Mapper;
import com.hzz.model.Tb1;
import com.hzz.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
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
    private TestService testService;

    @Resource
    private Tb1Mapper tb1Mapper;

    @RequestMapping("/sayHi")
    public String sayHi(String name) {
        LOGGER.debug("this is trace log");
        LOGGER.info("this is info log");
        return "hi, " + name;
    }

    @RequestMapping("/select")
    public Tb1 select(Long id) {
        return testService.selectById(id);
    }

    @RequestMapping("/selectMany")
    public String selectMany() {
        testService.selectManyTimes();
        return "success";
    }

    @RequestMapping("/trac")
    public String testTransaction(String val1, String val2, String rollback) {
        try {
            Tb1 t = tb1Mapper.selectByPrimaryKey(1L);
            System.out.println(t);

            testService.add(val1, val2, Boolean.valueOf(rollback));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Tb1 t = tb1Mapper.selectByPrimaryKey(1L);
        System.out.println(t);
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
