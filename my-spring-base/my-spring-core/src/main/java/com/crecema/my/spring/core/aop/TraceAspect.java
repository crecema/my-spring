package com.crecema.my.spring.core.aop;

import com.crecema.my.spring.common.domain.JokesRequest;
import com.crecema.my.spring.common.domain.JokesResponse;
import com.crecema.my.spring.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TraceAspect {

    @Pointcut("execution(* com.crecema.my.spring.common.client.ApiSpaceClient.getJokesByRandom(..))")
    public void getJokesByRandom() {}

    @Before("getJokesByRandom()")
    public void beforeGetJokesByRandom(JoinPoint joinPoint) {
        JokesRequest request = (JokesRequest) joinPoint.getArgs()[0];
        log.info("getJokesByRandom, request: {}", JsonUtils.toJson(request));
    }

    @AfterReturning(value = "getJokesByRandom()", argNames = "response", returning = "response")
    public void afterGetJokesByRandom(JokesResponse response) {
        log.info("getJokesByRandom response: {}", JsonUtils.toJson(response));
    }

}
