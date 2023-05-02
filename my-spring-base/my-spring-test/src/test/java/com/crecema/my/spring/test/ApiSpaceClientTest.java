package com.crecema.my.spring.test;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ActiveProfiles("default")
@SpringJUnitConfig(classes = BeansConfig.class)
@TestPropertySource("classpath:config.properties")
public class ApiSpaceClientTest {

    @Autowired
    private ApiSpaceClient apiSpaceClient;

    @Value("${api_space_url}")
    private String apiSpaceUrl;

    @Test
    public void testGetJokesByRandom() {
        Assertions.assertNotNull(apiSpaceClient);
    }

    @Test
    public void testPropertySource() {
        Assertions.assertEquals("https://eolink.o.apispace.com/xhdq/common/joke/getJokesByRandom", apiSpaceUrl);
    }

}
