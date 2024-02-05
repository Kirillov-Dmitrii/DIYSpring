package com.dimoglobit.service;

import org.framework.beens.factory.BeanNameAware;
import org.framework.beens.factory.stereotype.Component;

@Component
public class PromotionService implements BeanNameAware {
    private String beanName;

    public String getName() {
        return beanName;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
