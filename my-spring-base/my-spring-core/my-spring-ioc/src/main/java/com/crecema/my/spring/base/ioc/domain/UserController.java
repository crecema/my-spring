package com.crecema.my.spring.base.ioc.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserController {

    private UserService userService;

    public User user(int id) {
        return userService.getUser(id);
    }

    public List<User> userList(String name) {
        return userService.getUserList(name);
    }

}
