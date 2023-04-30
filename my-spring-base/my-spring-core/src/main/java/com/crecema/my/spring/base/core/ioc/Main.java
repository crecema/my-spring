package com.crecema.my.spring.base.core.ioc;

import com.crecema.my.spring.common.launcher.JokesLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        JokesLauncher jokesListener = applicationContext.getBean("jokesLauncher", JokesLauncher.class);
        jokesListener.launch();
    }

}
