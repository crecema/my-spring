package com.crecema.my.spring.boot.common;

import lombok.Getter;

public class CommonException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    public CommonException(ErrorCode errorCode) {
        super(buildMessage(errorCode.getCode(), errorCode.getDesc(), null));
        this.errorCode = errorCode;
    }

    public CommonException(ErrorCode errorCode, String message) {
        super(buildMessage(errorCode.getCode(), errorCode.getDesc(), message));
        this.errorCode = errorCode;
    }

    public CommonException(String message) {
        super(buildMessage(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getDesc(), message));
        this.errorCode = ErrorCode.UNKNOWN_ERROR;
    }

    private static String buildMessage(String code, String desc, String message) {
        return code + " | " + desc + " | " + message;
    }

}
