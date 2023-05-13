package com.crecema.my.spring.boot.simpleweb.common;

import lombok.Data;

@Data
public class CommonResponse<T> {

    private final int code;
    private final String desc;
    private final T data;

    private CommonResponse(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.desc = errorCode.getDesc();
        this.data = data;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(ErrorCode.SUCCESS, data);
    }

    public static <T> CommonResponse<T> failure(ErrorCode errorCode) {
        errorCode = errorCode == null ? ErrorCode.UNKNOWN_ERROR : errorCode;
        return new CommonResponse<>(errorCode, null);
    }

}
