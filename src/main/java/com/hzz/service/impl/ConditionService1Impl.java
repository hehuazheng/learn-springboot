package com.hzz.service.impl;

import com.hzz.service.ConditionService;
import com.hzz.service.MyCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * @author: hezz
 */
@Service
@Conditional(MyCondition.class)
public class ConditionService1Impl implements ConditionService {
    public String getCondition() {
        return ConditionService1Impl.class.getName();
    }
}
