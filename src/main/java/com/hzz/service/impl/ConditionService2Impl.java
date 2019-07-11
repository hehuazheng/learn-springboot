package com.hzz.service.impl;

import com.hzz.service.ConditionService;
import com.hzz.service.MyReverseCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

/**
 * @author: hezz
 */
@Service
@Conditional(MyReverseCondition.class)
public class ConditionService2Impl implements ConditionService {
    public String getCondition() {
        return ConditionService2Impl.class.getName();
    }
}
