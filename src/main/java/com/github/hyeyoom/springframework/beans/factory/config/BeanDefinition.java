package com.github.hyeyoom.springframework.beans.factory.config;

import java.lang.reflect.Constructor;

/**
 * bean 인스턴스를 설명하는 역할을 한다. bean 인스턴스 생성 방법에 대한 것이 여기에 적혀있다고 생각하면 된다.
 */
public interface BeanDefinition {

    /**
     * bean의 이름을 반환하는 클래스.
     * bean의 이름을 결정하는 책임이 이곳에 있는 것이 어색하나, 구현 편의성을 위해 이곳에.
     */
    String getBeanName();

    /**
     * 빈의 원본 클래스. 이 필드가 빈 생성에 사용됨
     */
    Class<?> getOriginalClass();

    /**
     * 빈 생성자
     */
    Constructor<?> getBeanConstructor();

    /**
     * 이 빈의 의존성
     */
    Class<?>[] getDependencies();

    /**
     * Lazy 로딩되는 BeanDefinition인가?
     */
    boolean isLazyInit();
}
