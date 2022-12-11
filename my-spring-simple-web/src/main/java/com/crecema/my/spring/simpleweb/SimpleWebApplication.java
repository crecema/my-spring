package com.crecema.my.spring.simpleweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.crecema.my.spring"})
public class SimpleWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleWebApplication.class, args);
    }

}
