package com.dimoglobit;

import com.dimoglobit.service.ProductService;
import com.dimoglobit.service.PromotionService;
import org.framework.beens.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
	// write your code here
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.instantiate("com.dimoglobit.service");
        beanFactory.populateProperties();
        beanFactory.injectBeanNames();

        ProductService productService = (ProductService) beanFactory.getBean("productService");
        System.out.println("Bean name:" + productService.getPromotionService().getName()) ;
    }
}
