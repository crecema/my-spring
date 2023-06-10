package com.crecema.my.spring.boot.simpleweb.service.impl;

import com.crecema.my.spring.boot.common.CommonException;
import com.crecema.my.spring.boot.common.ErrorCode;
import com.crecema.my.spring.boot.common.client.ApiSpaceClient;
import com.crecema.my.spring.boot.common.client.domain.JokesRequest;
import com.crecema.my.spring.boot.common.client.domain.JokesResponse;
import com.crecema.my.spring.boot.simpleweb.service.JokeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JokeServiceImpl implements JokeService {

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
    public List<String> getJokes(int number) {
        if (number > 20) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER, "number must be less than 20");
        }
        JokesRequest request = new JokesRequest(number);
        JokesResponse jokesResponse = apiSpaceClient.getJokesByRandom(request);
        return Optional.ofNullable(jokesResponse)
                .map(JokesResponse::getResult)
                .map(result -> result.stream()
                        .map(JokesResponse.Joke::getContent)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

}
