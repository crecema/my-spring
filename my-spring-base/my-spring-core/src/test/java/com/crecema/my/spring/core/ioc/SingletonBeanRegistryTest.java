package com.crecema.my.spring.core.ioc;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.service.JokesService;
import com.crecema.my.spring.common.service.JokesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;

/**
 * 单例 Bean 注册表
 * SingletonBeanRegistry 的直接实现是 DefaultSingletonBeanRegistry，核心数据结构是三个 Map，
 * 分别是 singletonObjects、earlySingletonObjects、singletonFactories，又被称为三级缓存。
 * * singletonObjects：存放完全初始化好的单例 Bean，被称为一级缓存
 * * earlySingletonObjects：存放早期暴露的单例 Bean，被称为二级缓存
 * * singletonFactories：存放 Bean 工厂，用于创建 Bean，被称为三级缓存
 * 这个类的作用是注册单例 Bean，单例 Bean 是指在整个容器中只有一个实例的 Bean。
 * 另外这个类非常重要，是 Spring 单例 Bean 的核心，也是 Spring 事务的核心。
 * DefaultListableBeanFactory 是 DefaultSingletonBeanRegistry 的子类，拥有其所有的功能。
 */
public class SingletonBeanRegistryTest {

    @Test
    public void testDefaultSingletonBeanRegistry() {
        SingletonBeanRegistry singletonBeanRegistry = new DefaultSingletonBeanRegistry();
        singletonBeanRegistry.registerSingleton("apiSpaceClient", new ApiSpaceClient());
        JokesService jokesService = new JokesServiceImpl((ApiSpaceClient) singletonBeanRegistry.getSingleton("apiSpaceClient"));
        singletonBeanRegistry.registerSingleton("jokesService", jokesService);
        Assertions.assertTrue(singletonBeanRegistry.containsSingleton("jokesService"));
        Assertions.assertEquals(2, singletonBeanRegistry.getSingletonCount());
        Assertions.assertEquals(2, singletonBeanRegistry.getSingletonNames().length);
    }

}
