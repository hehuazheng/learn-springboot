package com.hzz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hezz
 */
@Configuration
public class MyConfiguration {
    @Bean
    public MyProxyCreator getProxyCreator() {
        return new MyProxyCreator();
    }
}
