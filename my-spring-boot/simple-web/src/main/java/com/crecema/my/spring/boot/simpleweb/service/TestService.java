package com.crecema.my.spring.boot.simpleweb.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    public void testLog() {
        log.trace("this is a test {} log", "trace");
        log.debug("this is a test {} log", "debug");
        log.info("this is a test {} log", "info");
        log.warn("this is a test {} log", "warn");
        log.error("this is a test {} log", "error");
    }

}
