package com.crecema.my.spring.core.ioc;

import com.crecema.my.spring.common.launcher.JokesLauncher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext;
        if (((int) (Math.random() * 100)) % 2 == 0) {
            applicationContext = new AnnotationConfigApplicationContext(BeansConfig.class);
            log.info("Using annotation config application context");
        } else {
            applicationContext = new ClassPathXmlApplicationContext("beans.xml");
            log.info("Using xml config application context");
        }
        JokesLauncher jokesListener = applicationContext.getBean("jokesLauncher", JokesLauncher.class);
        jokesListener.launch();
    }

}
