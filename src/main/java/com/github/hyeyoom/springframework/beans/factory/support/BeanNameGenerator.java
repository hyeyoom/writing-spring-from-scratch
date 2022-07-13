package com.github.hyeyoom.springframework.beans.factory.support;

public class BeanNameGenerator {

    public static String determineBeanNameFromClass(Class<?> clazz) {
        final String name = clazz.getSimpleName();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }
}
