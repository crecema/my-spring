package com.crecema.my.spring.common.client;

import com.crecema.my.spring.common.domain.JokesRequest;
import com.crecema.my.spring.common.domain.JokesResponse;
import com.crecema.my.spring.common.util.JsonUtils;
import org.junit.jupiter.api.Test;

public class ApiSpaceClientTest {

    @Test
    public void testGetJokes() {
        ApiSpaceClient apiSpaceClient = new ApiSpaceClient();
        JokesRequest request = JokesRequest.builder().pageSize(5).build();
        JokesResponse response = apiSpaceClient.getJokesByRandom(request);
        System.out.println(JsonUtils.toJson(response));
    }

}
