package com.crecema.my.spring.simpleweb.controller;

import com.crecema.my.spring.common.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/alive")
    public CommonResponse<Boolean> alive() {
        return CommonResponse.success(true);
    }

    @GetMapping("/exception")
    public CommonResponse<Boolean> exception() {
        int a = 1 / 0;
        return CommonResponse.success();
    }

}
