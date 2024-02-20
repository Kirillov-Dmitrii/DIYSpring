package com.dimoglobit.service;

import org.framework.beens.context.ApplicationListener;
import org.framework.beens.context.event.ContextCloseEvent;
import org.framework.beens.factory.BeanFactory;
import org.framework.beens.factory.BeanFactoryAware;
import org.framework.beens.factory.BeanNameAware;
import org.framework.beens.factory.InitializingBean;
import org.framework.beens.factory.stereotype.Component;

@Component
public class PromotionService implements BeanNameAware, InitializingBean, BeanFactoryAware, ApplicationListener<ContextCloseEvent> {
    private String beanName;
    private BeanFactory beanFactory;

    public String getName() {
        return beanName;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("properties set" + ProductService.class.getName());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void onApplicationEvent(ContextCloseEvent event) {
        System.out.println(">> ContextClosed EVENT");
    }
}
