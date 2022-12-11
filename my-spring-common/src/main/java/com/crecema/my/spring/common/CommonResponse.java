package com.crecema.my.spring.common;

import lombok.Getter;

@Getter
public class CommonResponse<T> {

    private final Integer code;
    private final String desc;
    private final T data;

    public CommonResponse(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.desc = errorCode.getDesc();
        this.data = data;
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(CommonErrorCode.SUCCESS, data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(CommonErrorCode.SUCCESS, null);
    }

    public static <T> CommonResponse<T> unknown() {
        return new CommonResponse<>(CommonErrorCode.UNKNOWN, null);
    }

    public static <T> CommonResponse<T> failure(ErrorCode errorCode) {
        return new CommonResponse<>(errorCode, null);
    }

}
