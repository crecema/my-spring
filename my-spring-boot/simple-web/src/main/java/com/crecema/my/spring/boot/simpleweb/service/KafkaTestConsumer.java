package com.crecema.my.spring.boot.simpleweb.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaTestConsumer {

    @KafkaListener(topics = "my_test_topic", groupId = "my_test_group")
    private void process(String message) {
        System.out.println(message);
    }

}
