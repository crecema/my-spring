package com.crecema.my.spring.boot.simpleweb.controller;

import com.crecema.my.spring.boot.common.CommonException;
import com.crecema.my.spring.boot.common.CommonResponse;
import com.crecema.my.spring.boot.common.ErrorCode;
import com.crecema.my.spring.boot.simpleweb.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/hello")
    public CommonResponse<String> test() {
        return CommonResponse.success("Hey guys, I'm very good!");
    }

    @GetMapping("/log")
    public CommonResponse<?> testLog() {
        testService.testLog();
        return CommonResponse.success();
    }

    @GetMapping("/exception")
    public CommonResponse<?> testException(@RequestParam String type) {
        if ("exception".equals(type)) {
            throw new RuntimeException("test exception");
        } else if ("commonException".equals(type)) {
            throw new CommonException(ErrorCode.INVALID_PARAMETER, "test common exception");
        } else {
            return CommonResponse.success();
        }
    }

}
