package com.dimoglobit.service;

import org.framework.beens.factory.stereotype.Component;

@Component
public class ProductService {
    PromotionService promotionService;

    public PromotionService getPromotionService() {
        return promotionService;
    }

    public void setPromotionService(PromotionService promotionService) {
        this.promotionService = promotionService;
    }
}
