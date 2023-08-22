package com.crecema.my.spring.context.aop.joke;

import com.crecema.my.spring.joke.launcher.JokeLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JokeApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop/spring.xml");

        JokeLauncher jokeLauncher = applicationContext.getBean("jokeLauncher", JokeLauncher.class);

        jokeLauncher.run();

    }

}
