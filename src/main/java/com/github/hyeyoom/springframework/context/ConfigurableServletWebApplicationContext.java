package com.github.hyeyoom.springframework.context;

import com.github.hyeyoom.springframework.beans.factory.BeanDefinitionStoreException;
import com.github.hyeyoom.springframework.beans.factory.BeansException;
import com.github.hyeyoom.springframework.beans.factory.NoSuchBeanDefinitionException;
import com.github.hyeyoom.springframework.beans.factory.config.BeanDefinition;
import com.github.hyeyoom.springframework.beans.factory.config.SingletonBeanRegistry;
import com.github.hyeyoom.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.github.hyeyoom.springframework.beans.factory.support.BeanNameGenerator;
import com.github.hyeyoom.springframework.context.annotation.BeanDefinitionReader;
import com.github.hyeyoom.springframework.context.annotation.ComponentClassScanner;
import com.github.hyeyoom.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigurableServletWebApplicationContext
        implements ConfigurableApplicationContext, BeanDefinitionRegistry, SingletonBeanRegistry {


    private final BeanDefinitionReader reader;
    private final String[] basePackages;

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    private final Map<String, Object> singletonBeans = new HashMap<>();

    public ConfigurableServletWebApplicationContext(String[] basePackages) {
        reader = new BeanDefinitionReader(this);
        this.basePackages = basePackages;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        final String beanName = BeanNameGenerator.determineBeanNameFromClass(requiredType);
        final Object maybeBean = getSingleton(beanName);
        if (maybeBean != null) {
            return requiredType.cast(maybeBean);
        }

        final BeanDefinition bd = getBeanDefinition(beanName);
        final Class<?>[] dependencies = bd.getDependencies();
        final List<Object> depsInstances = getDependencyInstances(dependencies);
        return instantiate(requiredType, bd, depsInstances);
    }

    private <T> T instantiate(Class<T> requiredType, BeanDefinition bd, List<Object> depsInstances) {
        final Constructor<?> ctor = bd.getBeanConstructor();
        try {
            final Object bean = ctor.newInstance(depsInstances.toArray());
            return requiredType.cast(bean);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Unable to instantiate bean");
        }
    }

    private List<Object> getDependencyInstances(Class<?>[] dependencies) {
        final List<Object> depsInstances = new ArrayList<>();
        for (Class<?> dependency : dependencies) {
            final String dependencyBeanName = BeanNameGenerator.determineBeanNameFromClass(dependency);
            final Object maybeDependencyBean = getSingleton(dependencyBeanName);
            if (maybeDependencyBean != null) {
                depsInstances.add(maybeDependencyBean);
            } else {
                final Object dependencyBean = getBean(dependency);
                registerSingleton(dependencyBeanName, dependencyBean);
                depsInstances.add(dependencyBean);
            }
        }
        return depsInstances;
    }

    @Override
    public void refresh() {
        // 1. 대상 클래스 찾기
        final Class<? extends Annotation>[] annotations = new Class[] { Component.class };
        final Set<Class<?>> components = ComponentClassScanner.scan(annotations, basePackages);

        // 2. BeanDefinition 생성 및 등록
        reader.register(components.toArray(new Class[0]));

        // 3. bean 생성
        for (BeanDefinition beanDefinition : beanDefinitionMap.values()) {
            final Object bean = getBean(beanDefinition.getOriginalClass());
            registerSingleton(beanDefinition.getBeanName(), bean);
        }
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionStoreException {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException {
        final BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("BeanDefinition does not exist.");
        }
        return beanDefinition;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonBeans.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletonBeans.get(beanName);
    }
}
