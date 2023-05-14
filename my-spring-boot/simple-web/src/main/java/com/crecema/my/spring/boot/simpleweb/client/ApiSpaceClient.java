package com.crecema.my.spring.boot.simpleweb.client;

import com.crecema.my.spring.boot.simpleweb.client.domain.JokesRequest;
import com.crecema.my.spring.boot.simpleweb.client.domain.JokesResponse;
import com.crecema.my.spring.boot.simpleweb.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class ApiSpaceClient {

    private static final String API_URL = "https://eolink.o.apispace.com/xhdq/common/joke/getJokesByRandom";

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public JokesResponse getJokesByRandom(JokesRequest request) {
        String body = buildQuery(JsonUtils.toMap(request, String.class, String.class));
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.ofString(body, StandardCharsets.UTF_8))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("X-APISpace-Token", "nqygaah1x6rl2825zqf4655sqvvd47na")
                .header("Authorization-Type", "apikey")
                .build();
        HttpResponse<String> httpResponse = null;
        try {
            httpResponse = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return Optional.ofNullable(httpResponse)
                    .filter(response -> Objects.equals(response.statusCode(), 200))
                    .map(HttpResponse::body)
                    .map(bodyString -> JsonUtils.toObject(bodyString, JokesResponse.class))
                    .orElseThrow();
        } catch (Exception e) {
            log.error("getJokesByRandom failed, request:{}, httpResponse:{}", JsonUtils.toJson(request), JsonUtils.toJson(httpResponse));
            return null;
        }
    }

    private String buildQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            query.append(key).append("=").append(value).append("&");
        }
        if (query.length() > 0) {
            query.deleteCharAt(query.length() - 1);
        }
        return query.toString();
    }

}