package com.crecema.my.spring.base.ioc.domain;

import org.springframework.beans.factory.FactoryBean;

public class UserRepositoryFactoryBean implements FactoryBean<UserRepository> {

    private UserRepository userRepository;

    @Override
    public UserRepository getObject() throws Exception {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    @Override
    public Class<?> getObjectType() {
        return UserRepository.class;
    }
}
