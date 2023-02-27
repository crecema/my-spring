package com.crecema.my.spring.base.ioc.domain;

import java.util.List;
import java.util.Objects;

public class UserRepository {

    public static List<User> USER_POOL = List.of(
            new User(1001, "mahaoran", Sex.MALE, 24),
            new User(1002, "mahaoran", Sex.MALE, 24),
            new User(1003, "mahaoran", Sex.MALE, 24),
            new User(1004, "mahaoran", Sex.MALE, 24)
    );

    public User selectOne(Integer id) {
        return USER_POOL.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public List<User> selectList(String name) {
        return USER_POOL.stream()
                .filter(user -> Objects.equals(user.getName(), name))
                .toList();
    }

    public static UserRepository getInstance() {
        return new UserRepository();
    }

}
