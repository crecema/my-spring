package com.crecema.my.spring.common.service;

import com.crecema.my.spring.common.client.ApiSpaceClient;
import com.crecema.my.spring.common.domain.JokesRequest;
import com.crecema.my.spring.common.domain.JokesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class JokesServiceImpl implements JokesService {

    private final ApiSpaceClient apiSpaceClient;

    @Override
    public String getJoke() {
        JokesRequest request = new JokesRequest(1);
        JokesResponse jokesResponse = apiSpaceClient.getJokesByRandom(request);
        return Optional.ofNullable(jokesResponse)
                .map(JokesResponse::getResult)
                .map(result -> result.get(0))
                .map(JokesResponse.Joke::getContent)
                .orElse(null);
    }

    @Override
    public List<String> getJokes() {
        JokesRequest request = new JokesRequest(10);
        JokesResponse jokesResponse = apiSpaceClient.getJokesByRandom(request);
        return Optional.ofNullable(jokesResponse)
                .map(JokesResponse::getResult)
                .map(result -> result.stream()
                        .map(JokesResponse.Joke::getContent)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

}
