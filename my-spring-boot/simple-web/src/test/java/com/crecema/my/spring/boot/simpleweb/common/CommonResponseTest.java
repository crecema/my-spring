package com.crecema.my.spring.boot.simpleweb.common;

import com.crecema.my.spring.boot.simpleweb.util.JsonUtils;
import org.junit.jupiter.api.Test;

public class CommonResponseTest {

    @Test
    public void testSuccess() {
        CommonResponse<String> response = CommonResponse.success("this is a success response");
        System.out.println(JsonUtils.toJson(response));
    }

    @Test
    public void testFailure() {
        CommonResponse<String> response = CommonResponse.failure(ErrorCode.INVALID_PARAMETER);
        System.out.println(JsonUtils.toJson(response));

        response = CommonResponse.failure(null);
        System.out.println(JsonUtils.toJson(response));
    }

}
