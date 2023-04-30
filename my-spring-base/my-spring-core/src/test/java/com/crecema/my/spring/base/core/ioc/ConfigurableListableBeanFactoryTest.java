package com.crecema.my.spring.base.core.ioc;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.launcher.JokesLauncher;
import com.crecema.my.spring.common.service.JokesServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * 可配置的可列举的 Bean 工厂
 * ConfigurableListableBeanFactory 的直接实现是 DefaultListableBeanFactory
 * ConfigurableListableBeanFactory 的作用是配置和列举 Bean，也就是获取容器中所有的 Bean。
 */
public class ConfigurableListableBeanFactoryTest {

    @Test
    public void test() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition apiSpaceClientDefinition = new RootBeanDefinition(ApiSpaceClient.class);
        defaultListableBeanFactory.registerBeanDefinition("apiSpaceClient", apiSpaceClientDefinition);

        RootBeanDefinition jokesServiceDefinition = new RootBeanDefinition(JokesServiceImpl.class);
        jokesServiceDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
        defaultListableBeanFactory.registerBeanDefinition("jokesService", jokesServiceDefinition);

        RootBeanDefinition jokesLauncherDefinition = new RootBeanDefinition(JokesLauncher.class);
        jokesLauncherDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
        defaultListableBeanFactory.registerBeanDefinition("jokesLauncher", jokesLauncherDefinition);

        defaultListableBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) {
                System.out.println("postProcessBeforeInitialization: " + beanName);
                return bean;
            }
        });

        ConfigurableListableBeanFactory configurableListableBeanFactory = (ConfigurableListableBeanFactory) defaultListableBeanFactory;

        configurableListableBeanFactory.preInstantiateSingletons();
    }

}
