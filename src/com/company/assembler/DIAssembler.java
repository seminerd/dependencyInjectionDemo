package com.company.assembler;


import com.company.dependency.BeanId;
import com.company.dependency.MarkInject;
import com.company.dependent.Bike;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import org.reflections.Reflections;


public class DIAssembler {
    HashMap<String,Object> beanMap = new HashMap<>();

   public <T> T performInject(Class<T> injected) throws InstantiationException, IllegalAccessException {
       Reflections reflections = new Reflections("com.company");
            T returnOb = injected.newInstance();
            List<Method> setterMethodArrays = Arrays.stream(injected.getMethods()).filter((Method a) -> a.isAnnotationPresent(MarkInject.class)).collect(Collectors.toList());
            setterMethodArrays.forEach(method -> {
                String beanIdToInject = method.getAnnotation(MarkInject.class).id();
                Class<?> injectType = Arrays.stream(method.getParameterTypes()).findFirst().isPresent() ? Arrays.stream(method.getParameterTypes()).findFirst().get() : null;
                try {
                    Class<?> implementation = reflections.getSubTypesOf(injectType).stream().filter(
                            (Object e) -> {
                                Class<?> clazz = (Class<?>)e;
                                return clazz.getAnnotation(BeanId.class).id().equals(beanIdToInject);
                            }
                    ).findFirst().get();
                    beanMap.put(beanIdToInject,implementation.newInstance());
                    method.invoke(returnOb,beanMap.get(beanIdToInject));
                } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            return returnOb;
    }
}
