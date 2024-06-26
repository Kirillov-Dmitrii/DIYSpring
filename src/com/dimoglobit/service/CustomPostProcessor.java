package com.dimoglobit.service;

import org.framework.beens.factory.config.BeanPostProcessor;
import java.lang.reflect.InvocationTargetException;

public class CustomPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws InvocationTargetException, IllegalAccessException {
        System.out.println("postProcessor before init --- " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("postProcessor after init --- " + beanName);
        return bean;
    }
}
