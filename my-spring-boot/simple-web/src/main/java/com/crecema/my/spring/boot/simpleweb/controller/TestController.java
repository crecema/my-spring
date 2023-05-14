package com.crecema.my.spring.boot.simpleweb.controller;

import com.crecema.my.spring.boot.simpleweb.common.CommonResponse;
import com.crecema.my.spring.boot.simpleweb.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/log")
    public CommonResponse<?> testLog() {
        testService.testLog();
        return CommonResponse.success();
    }

}
