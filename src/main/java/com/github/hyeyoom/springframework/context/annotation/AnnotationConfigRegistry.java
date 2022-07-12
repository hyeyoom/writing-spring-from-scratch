package com.github.hyeyoom.springframework.context.annotation;

public interface AnnotationConfigRegistry {

    /**
     * 처리할 컴포넌트 클래스를 등록한다.
     */
    void register(Class<?>... componentClasses);

    /**
     * 명시된 베이스 패키지내에서 스캔을 수행한다.
     */
    void scan(String... basePackages);
}
