package org.framework.beens.context;

import org.framework.beens.context.event.ContextCloseEvent;
import org.framework.beens.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ApplicationContext {
    private BeanFactory beanFactory =  new BeanFactory();

    public ApplicationContext(String basePackage) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        System.out.println("*********************Context is constructing****************");
        beanFactory.instantiate(basePackage);
        beanFactory.populateProperties();;
        beanFactory.injectBeanNames();
        beanFactory.initializingBeans();
    }

    public void close() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        beanFactory.close();
        for (Object bean : beanFactory.getSingletons().values()) {
            for (Type type : bean.getClass().getGenericInterfaces()) {
                if (type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) type;

                    Type firstParameter = parameterizedType.getActualTypeArguments()[0];
                    if (firstParameter.equals(ContextCloseEvent.class)) {
                        Method method = bean.getClass().getMethod("onApplicationEvent", ContextCloseEvent.class);
                        method.invoke(bean, new ContextCloseEvent());
                    }
                }
            }
        }
    }
}
