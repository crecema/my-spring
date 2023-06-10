package com.crecema.my.spring.boot.data.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.crecema.my.spring.boot.data.domain.enums.Sex;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user", autoResultMap = true)
public class User {

    private Integer id;

    private Long uid;

    private String name;

    private String email;

    private String phone;

    private String idNumber;

    private Integer status;

    private Sex sex;

    private Integer age;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private ExtraInfo extraInfo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExtraInfo {

        private Boolean isStudent;

        private String school;

    }

}