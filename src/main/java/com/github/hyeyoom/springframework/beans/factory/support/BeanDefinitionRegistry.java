package com.github.hyeyoom.springframework.beans.factory.support;

import com.github.hyeyoom.springframework.beans.factory.BeanDefinitionStoreException;
import com.github.hyeyoom.springframework.beans.factory.NoSuchBeanDefinitionException;
import com.github.hyeyoom.springframework.beans.factory.config.BeanDefinition;

/**
 * Bean의 정의를 가지고 있는 인터페이스. 이 구현체에 BeanDefinition을 정의한다.
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionStoreException;

    BeanDefinition getBeanDefinition(String beanName) throws NoSuchBeanDefinitionException;

}
