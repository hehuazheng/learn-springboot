package com.hzz.service.impl;

import com.hzz.service.ConditionService2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @author: hezz
 */
@Service
@ConditionalOnProperty(value = "service.name", havingValue = "true", matchIfMissing = true)
public class Cond2Impl2 implements ConditionService2 {
    public String getCond2() {
        return Cond2Impl2.class.getName();
    }
}
