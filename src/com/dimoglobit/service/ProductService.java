package com.dimoglobit.service;

import org.framework.beens.factory.InitializingBean;
import org.framework.beens.factory.annotation.Autowire;
import org.framework.beens.factory.annotation.PreDestroy;
import org.framework.beens.factory.stereotype.Component;

@Component
public class ProductService implements InitializingBean {

    @Autowire
    private PromotionService promotionService;

    public PromotionService getPromotionService() {
        return promotionService;
    }

    public void setPromotionService(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("properties set" + ProductService.class.getName());
    }

    @PreDestroy
    private void toKill() {
        System.out.println("Last word " + ProductService.class.getName());
    }
}
