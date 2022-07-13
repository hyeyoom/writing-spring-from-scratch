package com.github.hyeyoom.springframework.context.annotation;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

public final class ComponentClassScanner {

    public static Set<Class<?>> scan(Class<? extends Annotation>[] annotations, String ...basePackages) {
        final Set<Class<?>> components = new HashSet<>();
        for (String basePackage : basePackages) {
            final Set<Class<?>> foundComponents = scan(annotations, basePackage);
            components.addAll(foundComponents);
        }
        return components;
    }

    private static Set<Class<?>> scan(Class<? extends Annotation>[] annotations, String basePackage) {
        final Reflections reflections = new Reflections(basePackage, Scanners.TypesAnnotated);
        final Set<Class<?>> components = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            final Set<Class<?>> foundComponents = reflections.getTypesAnnotatedWith(annotation);
            components.addAll(foundComponents);
        }
        return components;
    }
}
