package com.crecema.my.spring.boot.simpleweb.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class SimpleTask {

    @Async
    @Scheduled(cron = "0/5 * * * * ?")
    public void run() {
        log.info("SimpleTask is running...");
    }

}
