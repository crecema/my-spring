package com.crecema.my.spring.base.core.ioc;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.service.JokesService;
import com.crecema.my.spring.common.service.JokesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 可列举的 Bean 工厂
 * ListableBeanFactory 的直接实现是 DefaultListableBeanFactory
 * ListableBeanFactory 的作用是列举 Bean，也就是获取容器中所有的 Bean。
 */
public class ListableBeanFactoryTest {

    @Test
    public void test() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition apiSpaceClientDefinition = new RootBeanDefinition(ApiSpaceClient.class);
        defaultListableBeanFactory.registerBeanDefinition("apiSpaceClient", apiSpaceClientDefinition);

        BeanDefinition jokesServiceDefinition = new RootBeanDefinition(JokesServiceImpl.class);
        jokesServiceDefinition.getConstructorArgumentValues().addGenericArgumentValue(defaultListableBeanFactory.getBean("apiSpaceClient"));
        defaultListableBeanFactory.registerBeanDefinition("jokesService", jokesServiceDefinition);
        defaultListableBeanFactory.registerAlias("jokesService", "jokesServiceImpl");

        ListableBeanFactory listableBeanFactory = (ListableBeanFactory) defaultListableBeanFactory;

        String[] beanDefinitionNames = listableBeanFactory.getBeanDefinitionNames();
        Assertions.assertEquals(2, beanDefinitionNames.length);

        String[] beanNamesForType = listableBeanFactory.getBeanNamesForType(JokesService.class);
        Assertions.assertEquals(1, beanNamesForType.length);

        // ...
    }

}
