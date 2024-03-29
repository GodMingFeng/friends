package com.example.friends.mapper.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -6303370178565218550L;

    @Id
    @KeySql(useGeneratedKeys = true)
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
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
