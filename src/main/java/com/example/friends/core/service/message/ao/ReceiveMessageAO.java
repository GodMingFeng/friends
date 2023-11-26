package com.example.friends.core.service.message.ao;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReceiveMessageAO implements Serializable {

    private static final long serialVersionUID = -3922169348333635845L;

    /**
     * 用户id
     */
    private String userUniqueId;

    /**
     * 性别
     */
    private Integer gender;
}
