package com.hzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.hzz"})
@ImportResource({"classpath:spring/applicationContext.xml"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
