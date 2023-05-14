package com.crecema.my.spring.boot.simpleweb.service;

import java.util.List;

public interface JokeService {

    String getJoke();

    List<String> getJokes(int number);

}
