package com.crecema.my.spring.boot.simpleweb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

@SpringBootTest
@AutoConfigureMockMvc
public class JokeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetJoke() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/joke/getJoke")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andDo(result -> System.out.println(result.getResponse().getContentAsString(Charset.defaultCharset())))
                .andReturn();
    }

}
