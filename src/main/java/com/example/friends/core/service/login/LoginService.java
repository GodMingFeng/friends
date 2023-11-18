package com.example.friends.core.service.login;

import com.example.friends.core.service.login.ao.JsCode2sessionAO;
import com.example.friends.core.service.login.bo.UserBO;

public interface LoginService {

    /**
     * 用户登录
     *
     * @param request 请求
     * @return 结果
     */
    String login(JsCode2sessionAO request);

    /**
     * openId
     *
     * @param openId openId
     * @return 结果
     */
    UserBO getUserByOpenId(String openId);
}
