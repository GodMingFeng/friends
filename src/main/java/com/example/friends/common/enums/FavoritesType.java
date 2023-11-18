package com.example.friends.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FavoritesType {
    /**
     * 活动
     */
    ACTIVITIES(0),
    ;

    private final Integer type;

}
