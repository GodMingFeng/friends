package com.example.friends.core.service.message.ao;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SendMessageAO implements Serializable {

    private static final long serialVersionUID = -3922169348333635845L;

    /**
     * 漂流瓶内容
     */
    private String content;

    /**
     * 用户id
     */
    private String userUniqueId;

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
