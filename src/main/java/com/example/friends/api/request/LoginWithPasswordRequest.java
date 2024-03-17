package com.example.friends.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginWithPasswordRequest implements Serializable {

    private static final long serialVersionUID = -1353822443914012456L;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;
}
