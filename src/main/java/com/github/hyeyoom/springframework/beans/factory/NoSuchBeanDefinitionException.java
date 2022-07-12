package com.github.hyeyoom.springframework.beans.factory;

public class NoSuchBeanDefinitionException extends RuntimeException {

    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }
}
