package com.crecema.my.spring.base.core.ioc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean 定义注册表
 * BeanDefinitionRegistry 的直接实现是 DefaultListableBeanFactory，
 * 核心数据结构是 beanDefinitionMap，是一个 Map<String, BeanDefinition>，key 是 beanName，value 是 BeanDefinition
 * 这个接口的作用是注册 BeanDefinition，BeanDefinition 是 Bean 的定义，包含了 Bean 的所有属性。
 */
public class BeanDefinitionRegistryTest {

    @Test
    public void testDefinitionRegistry() {
        BeanDefinitionRegistry beanDefinitionRegistry = new DefaultListableBeanFactory();

        BeanDefinition apiSpaceClientBeanDefinition = new RootBeanDefinition();
        apiSpaceClientBeanDefinition.setBeanClassName("com.crecema.my.spring.common.client.ApiSpaceClient");
        beanDefinitionRegistry.registerBeanDefinition("apiSpaceClient", apiSpaceClientBeanDefinition);

        BeanDefinition jokesServiceBeanDefinition = new RootBeanDefinition();
        jokesServiceBeanDefinition.setBeanClassName("com.crecema.my.spring.common.service.JokesServiceImpl");
        jokesServiceBeanDefinition.setDependsOn("apiSpaceClient");
        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0, beanDefinitionRegistry.getBeanDefinition("apiSpaceClient"));
        jokesServiceBeanDefinition.getConstructorArgumentValues().addArgumentValues(constructorArgumentValues);
        beanDefinitionRegistry.registerBeanDefinition("jokesService", jokesServiceBeanDefinition);

        Assertions.assertEquals(apiSpaceClientBeanDefinition, beanDefinitionRegistry.getBeanDefinition("apiSpaceClient"));
        Assertions.assertTrue(beanDefinitionRegistry.containsBeanDefinition("jokesService"));

    }

}

