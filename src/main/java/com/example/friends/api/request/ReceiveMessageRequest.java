package com.example.friends.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReceiveMessageRequest implements Serializable {

    private static final long serialVersionUID = 3637455717323501356L;

    /**
     * 性别
     */
    private Integer gender;
}
