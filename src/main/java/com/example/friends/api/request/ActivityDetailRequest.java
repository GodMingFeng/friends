package com.example.friends.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityDetailRequest implements Serializable {

    private static final long serialVersionUID = -2132821546076980728L;

    /**
     * 活动id
     */
    private Long id;
}
