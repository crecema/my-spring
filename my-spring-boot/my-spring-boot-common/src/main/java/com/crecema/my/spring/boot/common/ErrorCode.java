package com.crecema.my.spring.boot.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    SUCCESS("00000", "success"),

    CLIENT_ERROR("A0000", "client error"),
    INVALID_PARAMETER("A0001", "invalid parameter"),

    SERVER_ERROR("B0000", "server error"),
    SERVER_UNKNOWN_ERROR("B0001", "unknown error"),

    THIRD_PARTY_ERROR("C0000", "third party error"),
    THIRD_SERVICE_ERROR("C1000", "third service error"),
    DATABASE_ERROR("C2000", "database error"),
    CACHE_ERROR("C3000", "cache error"),
    MESSAGE_QUEUE_ERROR("C4000", "message queue error"),

    UNKNOWN_ERROR("11111", "unknown error");

    private final String code;
    private final String desc;

}

