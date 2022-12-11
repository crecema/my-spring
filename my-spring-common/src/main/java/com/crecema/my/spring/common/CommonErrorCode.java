package com.crecema.my.spring.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    SUCCESS(0, "success"),
    UNKNOWN(1, "unknown system exception");

    private final int code;
    private final String desc;

}
