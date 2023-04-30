package com.crecema.my.spring.common;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.launcher.JokesLauncher;
import com.crecema.my.spring.common.service.JokesService;
import com.crecema.my.spring.common.service.JokesServiceImpl;

public class Main {

    public static void main(String[] args) {
        ApiSpaceClient apiSpaceClient = new ApiSpaceClient();
        JokesService jokesService = new JokesServiceImpl(apiSpaceClient);
        JokesLauncher jokesLauncher = new JokesLauncher(jokesService);
        jokesLauncher.launch();
    }

}
