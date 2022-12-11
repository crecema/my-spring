package com.crecema.my.spring.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResponse<?> handleException(Exception e) {
        log.error("unknown exception: {}", e.getMessage());
        return CommonResponse.unknown();
    }

}
