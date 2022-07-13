package com.github.hyeyoom.application.controller;

import com.github.hyeyoom.application.service.UserService;
import com.github.hyeyoom.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String getMessageFromService() {
        return userService.getUserNameById(UUID.randomUUID().toString());
    }
}
