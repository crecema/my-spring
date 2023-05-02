package com.crecema.my.spring.web.controller;

import com.crecema.my.spring.common.service.JokesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JokesController {

    private final JokesService jokesService;

    @GetMapping("/getJoke")
    public String getJoke() {
        return jokesService.getJoke();
    }

}
