package com.crecema.my.spring.web;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.launcher.JokesLauncher;
import com.crecema.my.spring.common.service.JokesService;
import com.crecema.my.spring.common.service.JokesServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRootConfig {

    @Bean
    public ApiSpaceClient apiSpaceClient() {
        return new ApiSpaceClient();
    }

    @Bean
    public JokesService jokesService(ApiSpaceClient apiSpaceClient) {
        return new JokesServiceImpl(apiSpaceClient);
    }

    @Bean
    public JokesLauncher jokesLauncher(JokesService jokesService) {
        return new JokesLauncher(jokesService);
    }

}
