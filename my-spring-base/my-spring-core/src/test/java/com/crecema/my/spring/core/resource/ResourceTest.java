package com.crecema.my.spring.core.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Resource be used to get resource from classpath or file system or url.
 */
public class ResourceTest {

    @Test
    public void test() {
        Resource resource = new ClassPathResource("log4j2.xml");
        Assertions.assertTrue(resource.exists());
    }

}
