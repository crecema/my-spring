package com.crecema.my.spring.base.ioc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Sex {

    FEMALE(0, "female"),
    MALE(1, "male");

    private final int code;
    private final String desc;

    public static Sex valueOf(int code) {
        return Arrays.stream(Sex.values())
                .filter(sex -> sex.code == code)
                .findAny()
                .orElse(null);
    }

}
