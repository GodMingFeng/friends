package com.example.friends.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum FavoritesStatus {
    /**
     * 生效
     */
    ENABLE(0),
    /**
     * 失效
     */
    DISABLE(1);

    private final Integer status;

    public static FavoritesStatus get(Integer status) {
        for (var value : values()) {
            if (Objects.equals(value.getStatus(), status)) {
                return value;
            }
        }
        return null;
    }
}
