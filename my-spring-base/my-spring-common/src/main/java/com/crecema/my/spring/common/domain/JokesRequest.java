package com.crecema.my.spring.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JokesRequest {

    private Integer pageSize;

}
