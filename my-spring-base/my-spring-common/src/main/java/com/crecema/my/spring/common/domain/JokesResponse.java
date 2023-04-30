package com.crecema.my.spring.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JokesResponse {

    private String statusCode;
    private List<Joke> result;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Joke {
        private Integer id;
        private String content;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private Date updateTime;
    }

}
