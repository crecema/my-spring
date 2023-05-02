package com.crecema.my.spring.core.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ApplicationContextTest {

    @Test
    public void testAsResourceLoader() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ResourceLoader resourceLoader = (ResourceLoader) applicationContext;
        Resource beansResource = resourceLoader.getResource("classpath:beans.xml");
        System.out.println(beansResource);
    }

    @Test
    public void testAsEnvironmentCapable() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        EnvironmentCapable environmentCapable = (EnvironmentCapable) applicationContext;
        Environment environment = environmentCapable.getEnvironment();
    }

    @Test
    public void testAsApplicationEventPublisher() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationEventPublisher applicationEventPublisher = (ApplicationEventPublisher) applicationContext;
    }

    @Test
    public void testAsApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(applicationContext.getId());
        System.out.println(applicationContext.getApplicationName());
        System.out.println(applicationContext.getDisplayName());
        System.out.println(applicationContext.getStartupDate());
        System.out.println(applicationContext.getParent());
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        Assertions.assertNotNull(autowireCapableBeanFactory);
    }

    @Test
    public void testAsConfigurableApplicationContext() {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        applicationContext.refresh();
    }

}
