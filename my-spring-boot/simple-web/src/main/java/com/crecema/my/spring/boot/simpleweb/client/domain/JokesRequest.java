package com.crecema.my.spring.boot.simpleweb.client.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JokesRequest {

    private Integer pageSize;

}
