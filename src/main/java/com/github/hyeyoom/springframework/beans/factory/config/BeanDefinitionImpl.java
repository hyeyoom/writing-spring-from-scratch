package com.github.hyeyoom.springframework.beans.factory.config;

import com.github.hyeyoom.springframework.stereotype.Lazy;

import java.lang.reflect.Constructor;
import java.util.Arrays;

public class BeanDefinitionImpl implements BeanDefinition {

    private final Class<?> originalClass;
    private final Constructor<?> constructor;

    private final Class<?>[] dependencies;

    private final Boolean isLazyInit;

    public BeanDefinitionImpl(Class<?> clazz) {
        this.originalClass = clazz;
        final Constructor<?>[] ctors = clazz.getDeclaredConstructors();
        this.constructor = Arrays
                .stream(ctors)
                .max((ctor1, ctor2) -> ctor1.getParameterCount() - ctor2.getParameterCount())
                .orElseThrow();
        this.dependencies = this.constructor.getParameterTypes();
        this.isLazyInit = this.constructor.isAnnotationPresent(Lazy.class);
    }

    @Override
    public String getBeanName() {
        final String name = this.originalClass.getSimpleName();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    @Override
    public Class<?> getOriginalClass() {
        return originalClass;
    }

    @Override
    public Constructor<?> getBeanConstructor() {
        return constructor;
    }

    @Override
    public Class<?>[] getDependencies() {
        return dependencies;
    }

    @Override
    public boolean isLazyInit() {
        return isLazyInit;
    }
}
