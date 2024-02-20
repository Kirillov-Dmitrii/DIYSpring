package com.dimoglobit;
import org.framework.beens.context.ApplicationContext;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
	// write your code here
        ApplicationContext applicationContext = new ApplicationContext("com.dimoglobit.service");
        applicationContext.close();
    }
}
