package com.hzz;

import com.hzz.anno.MyPointcutAnno;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: hezz
 */
public class MyProxyCreator extends AbstractAutoProxyCreator implements PointcutAdvisor {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyProxyCreator.class);

    private MyPointcut myPointcut = new MyPointcut();
    private Advice myAdvice = new MyAdvice();
    private Map<String, Advised> advisedMap = new ConcurrentHashMap<>();

    @Override
    public Pointcut getPointcut() {
        return myPointcut;
    }

    @Override
    public Advice getAdvice() {
        return myAdvice;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    protected Object wrapIfNecessary(Object bean, String beanName, Object cacheKey) {
        if (bean instanceof Advised) {
            advisedMap.put(beanName, (Advised) bean);
        }
        return super.wrapIfNecessary(bean, beanName, cacheKey);
    }

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource targetSource) throws BeansException {
        Method[] methods = beanClass.getDeclaredMethods();
        for (Method m : methods) {
            if (myPointcut.matches(m, beanClass)) {
                Advised advised = this.advisedMap.get(beanName);
                if(advised != null) {
//                    Advisor[] advisors = advised.getAdvisors();
                    boolean added = false;
//                    for (int i = 0; i < advisors.length; i++) {
//                        if (advisors[i].getAdvice() instanceof TransactionInterceptor) {
//                            advised.addAdvisor(i, this);
//                            added = true;
//                            break;
//                        }
//                    }
                    if (!added) {
                        advised.addAdvisor(this);
                    }
                    return DO_NOT_PROXY;
                }
                return new Object[]{this};
            }
        }
        return DO_NOT_PROXY;
    }

    static class MyPointcut implements Pointcut, MethodMatcher {

        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return doMatch(method, aClass);
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> aClass, Object... objects) {
           return doMatch(method, aClass);
        }

        protected boolean doMatch(Method method, Class<?> aClass, Object... objects) {
            MyPointcutAnno mpa = method.getAnnotation(MyPointcutAnno.class);
            return mpa != null;
        }

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return this;
        }
    }

    static class MyAdvice implements MethodInterceptor {
        private static final Logger LOGGER = LoggerFactory.getLogger(MyAdvice.class);

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            LOGGER.info("before invoke");
            Object obj = invocation.proceed();
            LOGGER.info("after invoke");
            return obj;
        }
    }
}
