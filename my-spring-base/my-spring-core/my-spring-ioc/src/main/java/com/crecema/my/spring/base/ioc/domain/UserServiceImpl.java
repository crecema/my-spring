package com.crecema.my.spring.base.ioc.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService, InitializingBean, DisposableBean {

    @Setter
    private UserRepository userRepository;

    @Override
    public User getUser(Integer id) {
        return userRepository.selectOne(id);
    }

    @Override
    public List<User> getUserList(String name) {
        return userRepository.selectList(name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserServiceImpl.afterPropertiesSet()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("UserServiceImpl.destroy()");
    }

    public void myInit() {
        System.out.println("UserServiceImpl.myInit()");
    }

    public void myDestroy() {
        System.out.println("UserServiceImpl.myDestroy()");
    }
}
