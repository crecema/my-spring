package com.crecema.my.spring.base.ioc.domain;

import java.util.List;

public interface UserService {

    User getUser(Integer id);

    List<User> getUserList(String name);

}
