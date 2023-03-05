package com.crecema.my.spring.base.jdbc.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    private Integer id;

    private String name;

    private Integer sex;

    private Integer age;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
