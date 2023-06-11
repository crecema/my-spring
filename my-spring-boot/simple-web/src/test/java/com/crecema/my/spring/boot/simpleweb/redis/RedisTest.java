package com.crecema.my.spring.boot.simpleweb.redis;

import com.crecema.my.spring.boot.simpleweb.SimpleWebApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;

@SpringBootTest(classes = SimpleWebApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testRedis() {
        Set<String> keys = redisTemplate.keys("*");
        System.out.println(keys);
        List<String> list = redisTemplate.opsForList().range("list", 0, -1);
        System.out.println(list);
    }

}
