package com.example.friends.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnums {

    MALE(0, "男性"),
    FEMALE(1, "女性");

    private final Integer gender;

    private final String desc;
}
