package com.crecema.my.spring.boot.simpleweb.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiAuthAspect {

    @Pointcut("@annotation(com.crecema.my.spring.boot.simpleweb.aspect.ApiAuth)")
    private void pointcut() {}

    @Before("pointcut()")
    public void before() {
        System.out.println("before");
    }

}
