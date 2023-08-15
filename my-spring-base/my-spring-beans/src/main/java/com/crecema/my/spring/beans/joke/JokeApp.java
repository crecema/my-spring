package com.crecema.my.spring.beans.joke;


import com.crecema.my.spring.joke.launcher.JokeLauncher;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class JokeApp {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinition jokeApiClientDefinition = new GenericBeanDefinition();
        jokeApiClientDefinition.setBeanClassName("com.crecema.my.spring.joke.common.client.JokeApiClient");
        beanFactory.registerBeanDefinition("jokeApiClient", jokeApiClientDefinition);

        BeanDefinition jokeServiceDefinition = new GenericBeanDefinition();
        jokeServiceDefinition.setBeanClassName("com.crecema.my.spring.joke.service.impl.JokeServiceImpl");
        jokeServiceDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanFactory.getBean("jokeApiClient"));
        beanFactory.registerBeanDefinition("jokeService", jokeServiceDefinition);

        BeanDefinition jokeLauncherDefinition = new GenericBeanDefinition();
        jokeLauncherDefinition.setBeanClassName("com.crecema.my.spring.joke.launcher.JokeLauncher");
        jokeLauncherDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanFactory.getBean("jokeService"));
        beanFactory.registerBeanDefinition("jokeLauncher", jokeLauncherDefinition);

        JokeLauncher jokeLauncher = beanFactory.getBean("jokeLauncher", JokeLauncher.class);

        jokeLauncher.run();

    }

}
