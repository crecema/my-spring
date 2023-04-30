package com.crecema.my.spring.base.core.ioc;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.service.JokesService;
import com.crecema.my.spring.common.service.JokesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean 工厂
 * BeanFactory 是 Spring 的核心接口，是 Spring 框架的基础设施，面向 Spring 本身，被 Spring 本身使用。
 * BeanFactory 的直接实现是 DefaultListableBeanFactory，DefaultListableBeanFactory 是 DefaultSingletonBeanRegistry 的子类，
 * DefaultSingletonBeanRegistry 又是 SimpleAliasRegistry 的子类，所以 BeanFactory 间接实现了 AliasRegistry 和 SingletonBeanRegistry。
 * BeanFactory 的核心功能是管理 Bean 的生命周期，包括 Bean 的加载、注册、实例化、配置、初始化、销毁等。
 * BeanFactory 的另一个重要功能是依赖注入，包括构造器注入、setter 注入、字段注入等。
 * BeanFactory 的另一个重要功能是 AOP，BeanFactory 会为 Bean 创建代理对象。
 */
public class BeanFactoryTest {

    @Test
    public void test() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition apiSpaceClientDefinition = new RootBeanDefinition(ApiSpaceClient.class);
        defaultListableBeanFactory.registerBeanDefinition("apiSpaceClient", apiSpaceClientDefinition);

        BeanDefinition jokesServiceDefinition = new RootBeanDefinition(JokesServiceImpl.class);
        jokesServiceDefinition.getConstructorArgumentValues().addGenericArgumentValue(defaultListableBeanFactory.getBean("apiSpaceClient"));
        defaultListableBeanFactory.registerBeanDefinition("jokesService", jokesServiceDefinition);
        defaultListableBeanFactory.registerAlias("jokesService", "jokesServiceImpl");

        BeanFactory beanFactory = (BeanFactory) defaultListableBeanFactory;

        Object apiSpaceClient1 = beanFactory.getBean("apiSpaceClient");
        ApiSpaceClient apiSpaceClient2 = beanFactory.getBean(ApiSpaceClient.class);
        ApiSpaceClient apiSpaceClient3 = beanFactory.getBean("apiSpaceClient", ApiSpaceClient.class);
        Assertions.assertEquals(apiSpaceClient1, apiSpaceClient2);
        Assertions.assertEquals(apiSpaceClient2, apiSpaceClient3);

        String[] beanFactoryAliases = beanFactory.getAliases("jokesService");
        Assertions.assertEquals(1, beanFactoryAliases.length);

        Assertions.assertTrue(beanFactory.containsBean("jokesService"));
        Assertions.assertTrue(beanFactory.containsBean("jokesServiceImpl"));

        Assertions.assertEquals(JokesServiceImpl.class, beanFactory.getType("jokesService"));
        Assertions.assertTrue(beanFactory.isTypeMatch("jokesService", JokesService.class));

        Assertions.assertTrue(beanFactory.isSingleton("jokesService"));
    }

}
