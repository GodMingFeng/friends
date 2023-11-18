package com.example.friends.mapper.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
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
