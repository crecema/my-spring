package com.crecema.my.spring.boot.simpleweb.controller;

import com.crecema.my.spring.boot.simpleweb.common.CommonResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public CommonResponse<String> test() {
        return CommonResponse.success("Hey guys, I'm very good!");
    }

}
