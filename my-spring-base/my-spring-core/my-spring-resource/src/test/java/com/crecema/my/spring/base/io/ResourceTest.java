package com.crecema.my.spring.base.io;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourceTest {

    @Test
    public void test() throws IOException {
        Resource resource = new ClassPathResource("config");
        assertNotNull(resource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
        assertEquals("name=mahaoran", reader.readLine());
    }

}
