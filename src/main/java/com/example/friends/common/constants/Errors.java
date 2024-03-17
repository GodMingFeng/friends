package com.example.friends.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    AUTH_ERROR(1001, "未登录"),
    USER_ERROR(1002, "密码错误"),


    RECEIVE_LIMIT(2001, "收取上限"),
    NO_MESSAGE(2002, "暂时没有漂流瓶"),


    SYSTEM_ERROR(9999, "系统异常"),
    PARAMS_ERROR(9998, "参数异常"),
    ;

    private final Integer errorCode;

    private final String errorMsg;
}
