package com.example.friends.core.service.message.ao;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseMessageAO implements Serializable {

    private static final long serialVersionUID = -3922169348333635845L;

    /**
     * 用户id
     */
    private String userUuid;

    /**
     * 消息id
     */
    private String messageUuid;

    /**
     * 回复消息内容
     */
    private String content;
}
