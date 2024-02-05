package com.dimoglobit;

import com.dimoglobit.service.ProductService;
import org.framework.beens.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.instantiate("com.dimoglobit.service");
        ProductService productService = (ProductService)beanFactory.getBean("productService");
        System.out.println(productService.getPromotionService());

        try {
            beanFactory.populateProperties();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(productService.getPromotionService());
    }
}
