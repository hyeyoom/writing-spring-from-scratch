package com.github.hyeyoom.springframework.context.annotation;

import com.github.hyeyoom.springframework.beans.factory.config.BeanDefinitionImpl;
import com.github.hyeyoom.springframework.beans.factory.support.BeanDefinitionRegistry;

/**
 * 다음 두 가지 일을 수행한다.
 * 1. 클래스를 읽고 BeanDefinition을 만든다.
 * 2. Bean을 registry에 등록한다.
 */
public class BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    public BeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void register(Class<?>... componentClasses) {
        for (Class<?> componentClass : componentClasses) {
            registerBean(componentClass);
        }
    }

    private void registerBean(Class<?> componentClass) {
        final BeanDefinitionImpl bd = new BeanDefinitionImpl(componentClass);
        registry.registerBeanDefinition(bd.getBeanName(), bd);
    }
}
