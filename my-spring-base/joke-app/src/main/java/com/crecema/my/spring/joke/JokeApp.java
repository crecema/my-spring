package com.crecema.my.spring.joke;

import com.crecema.my.spring.joke.common.client.JokeApiClient;
import com.crecema.my.spring.joke.launcher.JokeLauncher;
import com.crecema.my.spring.joke.service.impl.JokeServiceImpl;

public class JokeApp {

    public static void main(String[] args) {
        new JokeLauncher(new JokeServiceImpl(new JokeApiClient())).run();
    }

}
