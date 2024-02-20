package org.framework.beens.factory;

import org.framework.beens.factory.annotation.Autowire;
import org.framework.beens.factory.annotation.PreDestroy;
import org.framework.beens.factory.config.BeanPostProcessor;
import org.framework.beens.factory.stereotype.Component;
import org.framework.beens.factory.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class BeanFactory {
    private Map<String, Object> singletons = new HashMap<>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public Object getBean(String beanName) {
        return  singletons.get(beanName);
    }

    public void instantiate(String basePackage) {

        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            String path = basePackage.replace('.', '/');

            Enumeration<URL> resources = classLoader.getResources(path);

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();

                File file = new File(resource.toURI());

                for (File classFile : file.listFiles()) {
                    String fileName = classFile.getName();

                    if (fileName.endsWith(".class")) {
                        String className = fileName.substring(0, fileName.lastIndexOf("."));
                        Class classObject = Class.forName(basePackage + "." + className);

                        if (classObject.isAnnotationPresent(Component.class) || classObject.isAnnotationPresent(Service.class)) {
                            System.out.println("Component: " + classObject);
                            Object instance = classObject.getDeclaredConstructor().newInstance();
                            String beanName = className.substring(0,1).toLowerCase() + className.substring(1);
                            singletons.put(beanName, instance);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getSingletons() {
        return singletons;
    }

    //Инъекция по типу
    public void populateProperties() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("populateProperties");
        for (Object object : singletons.values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowire.class)) {
                    for (Object dependency : singletons.values()) {
                        if (dependency.getClass().equals(field.getType())) {
                            String setterName =
                                    "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                            System.out.println("Setter name = " + setterName);
                            Method setter = object.getClass().getMethod(setterName, dependency.getClass());
                            setter.invoke(object, dependency);
                        }
                    }
                }
            }
        }
    }

    public void injectBeanNames() {
        for (String name : singletons.keySet()) {
            Object bean = singletons.get(name);
            if (bean instanceof BeanNameAware) {
                ((BeanNameAware) bean).setBeanName(name);
            }
        }
    }

    public void initializingBeans() throws InvocationTargetException, IllegalAccessException {
        for (String name : singletons.keySet()) {
            Object bean = singletons.get(name);
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessBeforeInitialization (bean, name);
            }

            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessAfterInitialization(bean, name);
            }
        }
    }

    public void addPostProcessor(BeanPostProcessor postProcessor) {
        beanPostProcessors.add(postProcessor);
        System.out.println("PostProcessor added");
    }

    public void close()  {
        for (Object bean : singletons.values()) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(PreDestroy.class)) {
                    method.setAccessible(true);
                    try {
                        method.invoke(bean);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (bean instanceof DisposableBean) {
                    ((DisposableBean) bean).destroy();
                }
            }
        }
    }
}
