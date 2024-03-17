package com.example.friends.core.service.user;

import com.example.friends.core.service.user.ao.UserSaveAO;
import com.example.friends.core.service.user.bo.UserBO;

public interface UserService {

    /**
     * 保存用户信息
     *
     * @param userSaveAO 保存请求
     * @return 结果
     */
    Boolean saveUser(UserSaveAO userSaveAO);

    /**
     * 通过uid查询用户信息
     *
     * @param uid uid
     * @return 结果
     */
    UserBO queryUserByUid(String uid);


    /**
     * 通过uid查询用户信息
     *
     * @param mobile   手机号
     * @param password 密码
     * @return 结果
     */
    UserBO queryUserByUid(String mobile, String password);

    /**
     * 创建未注册新用户
     *
     * @return 结果
     */
    UserBO createUserWithoutRegister();
}
