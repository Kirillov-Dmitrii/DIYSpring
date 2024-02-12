package com.dimoglobit.service;

import org.framework.beens.factory.annotation.PostConstruct;
import org.framework.beens.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommonAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws InvocationTargetException, IllegalAccessException {
        Method[] fields = bean.getClass().getDeclaredMethods();
        for (Method method : fields) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(bean);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }
}
