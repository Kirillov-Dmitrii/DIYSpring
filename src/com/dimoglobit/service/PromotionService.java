package com.dimoglobit.service;

import org.framework.beens.factory.BeanNameAware;
import org.framework.beens.factory.InitializingBean;
import org.framework.beens.factory.stereotype.Component;

@Component
public class PromotionService implements BeanNameAware, InitializingBean {
    private String beanName;

    public String getName() {
        return beanName;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("properties set");
    }
}
