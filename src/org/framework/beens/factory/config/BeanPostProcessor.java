package org.framework.beens.factory.config;

import java.lang.reflect.InvocationTargetException;

public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(Object bean, String beanName) throws InvocationTargetException, IllegalAccessException;
    Object postProcessAfterInitialization(Object bean, String beanName);
}
