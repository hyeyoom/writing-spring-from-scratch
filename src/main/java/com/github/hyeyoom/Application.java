package com.github.hyeyoom;

import com.github.hyeyoom.application.controller.UserController;
import com.github.hyeyoom.springframework.context.ConfigurableApplicationContext;
import com.github.hyeyoom.springframework.context.ConfigurableServletWebApplicationContext;

public class Application {

    public static void main(String[] args) {
        final String basePackage = Application.class.getPackageName();
        final String[] basePackages = {basePackage};
        final ConfigurableApplicationContext applicationContext =
                new ConfigurableServletWebApplicationContext(basePackages);
        applicationContext.refresh();
        final UserController bean = applicationContext.getBean(UserController.class);
        System.out.println("bean.getMessageFromService() = " + bean.getMessageFromService());
    }
}
