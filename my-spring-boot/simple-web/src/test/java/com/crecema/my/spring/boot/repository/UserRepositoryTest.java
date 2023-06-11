package com.crecema.my.spring.boot.repository;

import com.crecema.my.spring.boot.data.domain.entity.User;
import com.crecema.my.spring.boot.data.repository.UserRepository;
import com.crecema.my.spring.boot.simpleweb.SimpleWebApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SimpleWebApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetOneByEmail() {
        User user = userRepository.getOneByEmail("crece.ma@gmail.com");
        System.out.println(user);
        Assertions.assertNotNull(user);
    }

}
