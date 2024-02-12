package com.dimoglobit.service;

import org.framework.beens.factory.DisposableBean;
import org.framework.beens.factory.InitializingBean;
import org.framework.beens.factory.annotation.PostConstruct;
import org.framework.beens.factory.stereotype.Service;

@Service
public class PersonService implements InitializingBean, DisposableBean {

    private String name;
    private ProductService productService;

    @PostConstruct
    public void signal() {
        System.out.println("PostConstruct is coming");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void afterPropertiesSet() {
        System.out.println("properties set" + ProductService.class.getName());
    }

    @Override
    public void destroy() {
    }
}
