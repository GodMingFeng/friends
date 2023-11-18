package com.example.friends.core.service.login.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserBO implements Serializable {

    private static final long serialVersionUID = -5067900482913831719L;

    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 公众号唯一id
     */
    private String unionId;

    /**
     * 应用内唯一id
     */
    private String openId;

    /**
     * 会话key
     */
    private String sessionKey;

    /**
     * api key
     */
    private String accessToken;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;
}
