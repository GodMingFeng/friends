package com.example.friends.core.service.user.ao;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserSaveAO implements Serializable {

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 用户地区
     */
    private String region;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;
}
