package com.crecema.my.spring.boot.simpleweb.common;

import com.crecema.my.spring.common.util.JsonUtils;
import org.junit.jupiter.api.Test;

public class CommonExceptionTest {

    @Test
    public void testPrintStackTraceOnlyErrorCode() {
        try {
            throw new CommonException(ErrorCode.INVALID_PARAMETER);
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrintStackTraceOnlyMessage() {
        try {
            throw new CommonException("this is exception message");
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPrintStackTraceWithMessage() {
        try {
            throw new CommonException(ErrorCode.INVALID_PARAMETER, "this is exception message");
        } catch (CommonException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonFormat() {
        System.out.println(JsonUtils.toJson(new CommonException(ErrorCode.INVALID_PARAMETER)));
    }

}
