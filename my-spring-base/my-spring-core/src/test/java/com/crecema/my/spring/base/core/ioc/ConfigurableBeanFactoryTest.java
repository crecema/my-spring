package com.crecema.my.spring.base.core.ioc;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.service.JokesService;
import com.crecema.my.spring.common.service.JokesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.lang.Nullable;

/**
 * 可配置的 Bean 工厂
 * ConfigurableBeanFactory 的直接实现是 DefaultListableBeanFactory
 * ConfigurableBeanFactory 的作用是配置 Bean 工厂，也就是设置 Bean 工厂的各种属性。
 */
public class ConfigurableBeanFactoryTest {

    @Test
    public void test() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        BeanDefinition apiSpaceClientDefinition = new RootBeanDefinition(ApiSpaceClient.class);
        defaultListableBeanFactory.registerBeanDefinition("apiSpaceClient", apiSpaceClientDefinition);

        BeanDefinition jokesServiceDefinition = new RootBeanDefinition(JokesServiceImpl.class);
        jokesServiceDefinition.getConstructorArgumentValues().addGenericArgumentValue(defaultListableBeanFactory.getBean("apiSpaceClient"));
        defaultListableBeanFactory.registerBeanDefinition("jokesService", jokesServiceDefinition);
        defaultListableBeanFactory.registerAlias("jokesService", "jokesServiceImpl");

        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) defaultListableBeanFactory;
        configurableBeanFactory.setBeanClassLoader(this.getClass().getClassLoader());
        configurableBeanFactory.setCacheBeanMetadata(false);
        configurableBeanFactory.setParentBeanFactory(new DefaultListableBeanFactory());
        configurableBeanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) {
                if (bean instanceof JokesServiceImpl) {
                    System.out.println("JokesServiceImpl postProcessBeforeInitialization");
                }
                return bean;
            }
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof JokesServiceImpl) {
                    System.out.println("JokesServiceImpl postProcessAfterInitialization");
                }
                return bean;
            }
        });

        JokesService jokesService = configurableBeanFactory.getBean("jokesService", JokesService.class);
        Assertions.assertNotNull(jokesService);
    }

}
