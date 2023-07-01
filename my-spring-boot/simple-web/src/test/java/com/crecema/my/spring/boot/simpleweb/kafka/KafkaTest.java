package com.crecema.my.spring.boot.simpleweb.kafka;

import com.crecema.my.spring.boot.simpleweb.SimpleWebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(classes = SimpleWebApplication.class)
public class KafkaTest {

    private static final String TOPIC = "my_test_topic";
    private static final String GROUP = "my_test_group";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void testProducer() {
        kafkaTemplate.send("my_test_topic", "hello world from spring boot");
    }

    @Test
    void testConsumer() {

    }

}
