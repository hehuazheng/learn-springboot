package com.hzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Hello world!
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.hzz"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public MappingJackson2HttpMessageConverter fastJsonConvert() {
        return new MappingJackson2HttpMessageConverter();
    }
}
