package com.crecema.my.spring.boot.simpleweb.controller;

import com.crecema.my.spring.boot.common.CommonResponse;
import com.crecema.my.spring.boot.simpleweb.service.JokeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/joke")
@RequiredArgsConstructor
public class JokeController {

    private final JokeService jokeService;

    @GetMapping("/getJoke")
    public CommonResponse<String> getJoke() {
        return CommonResponse.success(jokeService.getJoke());
    }

    @GetMapping("/getJokes")
    public CommonResponse<List<String>> getJokes(@RequestParam int number) {
        return CommonResponse.success(jokeService.getJokes(number));
    }

}
