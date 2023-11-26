package com.example.friends.api.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SendMessageRequest implements Serializable {

    private static final long serialVersionUID = 3637455717323501356L;

    /**
     * 漂流瓶内容
     */
    private String content;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 性别
     */
    private Integer gender;
}
