package com.example.friends.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageStatus {

    SEND(0, "丢出"),
    RECEIVE(1, "被接收"),
    RESPONSE(2, "被回应"),
    DISABLE(99, "失效"),
    ;

    /**
     * 状态
     */
    private final Integer status;

    /**
     * 描述
     */
    private final String desc;


}
