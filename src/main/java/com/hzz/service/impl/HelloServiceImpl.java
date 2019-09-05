package com.hzz.service.impl;

import com.hzz.anno.MyPointcutAnno;
import com.hzz.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author: hezz
 */
@Service
public class HelloServiceImpl implements HelloService {
    @MyPointcutAnno
    @Override
    public String hello() {
        return "你好";
    }
}
