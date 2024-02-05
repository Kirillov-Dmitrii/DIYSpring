package com.dimoglobit;

import org.framework.beens.factory.BeanFactory;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.instantiate("com.dimoglobit.service");
    }
}
