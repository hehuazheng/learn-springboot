package com.hzz.empty;

import com.google.common.base.Joiner;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: hezz
 */
public class EmptyImplFactoryBean<T> implements FactoryBean<T> {
    private Class<T> interfaceClazz;

    public EmptyImplFactoryBean() {
    }

    public EmptyImplFactoryBean(Class<T> interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
    }

    @Override
    public T getObject() throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "invoke " + method.getName() + " with " + paramsToString(args);
            }

            protected String paramsToString(Object[] args) {
                if (args != null) {
                    return "[" + Joiner.on(',').join(args) + "]";
                }
                return "[]";
            }
        });
    }

    @Override
    public Class<T> getObjectType() {
        return interfaceClazz;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setInterfaceClazz(Class<T> interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
    }
}
