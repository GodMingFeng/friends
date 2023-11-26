package com.example.friends.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseMessageRequest implements Serializable {

    private static final long serialVersionUID = 3637455717323501356L;

    /**
     * 漂流瓶内容
     */
    private String content;

    /**
     * 消息uuid
     */
    private String messageUniqueId;
}
