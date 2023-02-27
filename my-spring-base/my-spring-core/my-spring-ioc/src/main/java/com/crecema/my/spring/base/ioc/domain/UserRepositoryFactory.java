package com.crecema.my.spring.base.ioc.domain;

public class UserRepositoryFactory {

    public UserRepository getInstance() {
        return new UserRepository();
    }

}
