package com.crecema.my.spring.boot.simpleweb.common;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String message;

    public CommonException(ErrorCode errorCode) {
        super(buildMessage(errorCode.getCode(), errorCode.getDesc(), null));
        this.errorCode = errorCode;
        this.message = null;
    }

    public CommonException(ErrorCode errorCode, String message) {
        super(buildMessage(errorCode.getCode(), errorCode.getDesc(), message));
        this.errorCode = errorCode;
        this.message = message;
    }

    public CommonException(String message) {
        super(buildMessage(ErrorCode.UNKNOWN_ERROR.getCode(), ErrorCode.UNKNOWN_ERROR.getDesc(), message));
        this.errorCode = ErrorCode.UNKNOWN_ERROR;
        this.message = message;
    }

    private static String buildMessage(int code, String desc, String message) {
        return code + " | " + desc + " | " + message;
    }

}
