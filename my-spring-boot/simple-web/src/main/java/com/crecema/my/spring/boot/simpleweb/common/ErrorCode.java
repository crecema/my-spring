package com.crecema.my.spring.boot.simpleweb.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS(0, "success"),
    FAILURE(1, "failure"),
    UNKNOWN_ERROR(-1, "unknown error"),
    INVALID_PARAMETER(1001, "invalid parameter"),
    ;

    private final int code;
    private final String desc;

}
