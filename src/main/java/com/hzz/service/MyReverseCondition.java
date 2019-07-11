package com.hzz.service;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: hezz
 */
public class MyReverseCondition extends MyCondition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return !super.matches(conditionContext, annotatedTypeMetadata);
    }
}
