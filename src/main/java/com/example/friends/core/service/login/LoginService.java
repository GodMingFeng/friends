package com.example.friends.core.service.login;

import com.example.friends.core.service.user.bo.UserBO;

public interface LoginService {

    /**
     * 用户登录
     *
     * @param mobile   手机号
     * @param password 密码
     * @return token token
     */
    String login(String mobile, String password);

    /**
     * 鉴权
     *
     * @param token token
     *              、
     * @return 用户
     */
    UserBO authCheck(String token);

    /**
     * 不使用密码登录
     *
     * @return 结果
     */
    String loginWithoutPassword();
}
