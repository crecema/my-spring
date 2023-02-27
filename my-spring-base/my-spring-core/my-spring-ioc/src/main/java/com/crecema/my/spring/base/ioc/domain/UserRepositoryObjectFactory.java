package com.crecema.my.spring.base.ioc.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

public class UserRepositoryObjectFactory implements ObjectFactory<UserRepository> {
    @Override
    public UserRepository getObject() throws BeansException {
        return new UserRepository();
    }
}
