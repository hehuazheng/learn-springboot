package com.hzz;

import com.google.common.collect.Maps;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: hezz
 */
@Component
public class MyContextInitializer2 implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment env = configurableApplicationContext.getEnvironment();
        Map<String, Object> map =  Maps.newHashMap();
        map.put("test.username2", "inmemoryuser2");
        env.getPropertySources().addFirst(new MapPropertySource("mapProperty2", map));
    }
}
