package com.example.friends.core.service.user.bo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserBO implements Serializable {

    private static final long serialVersionUID = -6303370178565218550L;

    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 唯一id
     */
    private String uniqueId;

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
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 密码
     */
    private String password;
}
