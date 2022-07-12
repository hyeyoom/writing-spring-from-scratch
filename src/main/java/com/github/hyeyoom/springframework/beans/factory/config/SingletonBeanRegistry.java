package com.github.hyeyoom.springframework.beans.factory.config;

/**
 * Interface that defines a registry for shared bean instances.
 * Can be implemented by {@link com.github.hyeyoom.springframework.beans.factory.BeanFactory}
 * implementations in order to expose their singleton management facility
 * in a uniform manner.
 *
 * 공용 Bean 인스턴스를 위한 레지스트리를 정의하는 인터페이스.
 * BeanFactory의 구현체로 구현할 수 있으며, 일관된 방법으로 싱글톤 관리 기능 노출을 하기 위함이다.
 */
public interface SingletonBeanRegistry {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
