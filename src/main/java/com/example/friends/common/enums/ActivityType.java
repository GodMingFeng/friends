package com.example.friends.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ActivityType {
    /**
     * 最新上架
     */
    NEWS(0),
    /**
     * 即将过期
     */
    SOON_EXPIRE(1),
    /**
     * 已过期
     */
    EXPIRE(2),
    ;

    private final Integer type;

    public static ActivityType get(Integer type) {
        for (var value : values()) {
            if (Objects.equals(value.getType(), type)) {
                return value;
            }
        }
        return null;
    }
}
