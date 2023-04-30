package com.crecema.my.spring.base.core.ioc;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.launcher.JokesLauncher;
import com.crecema.my.spring.common.service.JokesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;

/**
 * 可自动装配的 Bean 工厂
 * AutowireCapableBeanFactory 的直接实现是 AbstractAutowireCapableBeanFactory
 * AutowireCapableBeanFactory 的作用是自动装配 Bean，也就是自动为 Bean 的属性赋值。
 */
public class AutowireCapableBeanFactoryTest {

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition apiSpaceClientDefinition = new RootBeanDefinition(ApiSpaceClient.class);
        defaultListableBeanFactory.registerBeanDefinition("apiSpaceClient", apiSpaceClientDefinition);

        RootBeanDefinition jokesServiceDefinition = new RootBeanDefinition(JokesServiceImpl.class);
        jokesServiceDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
        defaultListableBeanFactory.registerBeanDefinition("jokesService", jokesServiceDefinition);

        RootBeanDefinition jokesLauncherDefinition = new RootBeanDefinition(JokesLauncher.class);
        jokesLauncherDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
        defaultListableBeanFactory.registerBeanDefinition("jokesLauncher", jokesLauncherDefinition);

        AutowireCapableBeanFactory autowireCapableBeanFactory = (AutowireCapableBeanFactory) defaultListableBeanFactory;

        JokesLauncher jokesLauncher = autowireCapableBeanFactory.getBean("jokesLauncher", JokesLauncher.class);

        Field jokesServiceField = JokesLauncher.class.getDeclaredField("jokesService");
        jokesServiceField.setAccessible(true);
        Object jokesService = jokesServiceField.get(jokesLauncher);
        Assertions.assertEquals(jokesService, autowireCapableBeanFactory.getBean("jokesService"));
    }

}
