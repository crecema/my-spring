package com.crecema.my.spring.base.context;

import com.crecema.my.spring.base.ioc.domain.UserController;
import com.crecema.my.spring.base.ioc.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ClassPathXmlApplicationContextTest {

    @Test
    public void testInit() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
    }

    @Test
    public void testGetBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserController userController = applicationContext.getBean("userController", UserController.class);
        assertEquals("mahaoran", userController.user(1001).getName());
    }

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        UserRepository userRepositoryV1 = (UserRepository) applicationContext.getBean("userRepositoryV1");
        assertNotNull(userRepositoryV1);

        UserRepository userRepositoryV2 = (UserRepository) applicationContext.getBean("userRepositoryV2");
        assertNotNull(userRepositoryV2);

        UserRepository userRepositoryV3 = (UserRepository) applicationContext.getBean("userRepositoryV3");
        assertNotNull(userRepositoryV3);

        UserRepository userRepositoryV4 = (UserRepository) applicationContext.getBean("userRepositoryV4");
        assertNotNull(userRepositoryV4);
        assertNotNull(applicationContext.getBean("&userRepositoryV4"));
    }

}
