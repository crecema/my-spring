package com.crecema.my.spring.base.context;

import com.crecema.my.spring.base.ioc.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.Resource;

import static org.junit.jupiter.api.Assertions.*;

public class GenericApplicationContextTest {

    @Test // 作为ResourceLoader，提供资源加载能力，使用继承+组合实现
    public void testAsResourceLoader() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        Resource resource = applicationContext.getResource("classpath:beans.xml");
        assertNotNull(resource);
    }

    @Test // 作为BeanFactory，提供IOC能力，使用组合实现（内部维护一个DefaultListableBeanFactory）
    public void testAsBeanFactory() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBeanDefinition("userRepository", newBeanDefinition(UserRepository.class));
        applicationContext.refresh();
        UserRepository userRepository = (UserRepository) applicationContext.getBean("userRepository");
        assertNotNull(userRepository);
    }

    @Test // 作为ApplicationContext
    public void testAsApplicationContext() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.refresh();
        System.out.println(applicationContext.getId());
        System.out.println(applicationContext.getApplicationName());
        System.out.println(applicationContext.getDisplayName());
        System.out.println(applicationContext.getStartupDate());
        System.out.println(applicationContext.getParent());
        System.out.println(applicationContext.getAutowireCapableBeanFactory());
    }

    @Test // ApplicationContext扩展，提供配置 ApplicationContext 的能力
    public void testAsConfigurableApplicationContext() {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.refresh();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment);
    }

    private BeanDefinition newBeanDefinition(Class<?> beanClass) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        return beanDefinition;
    }

}
