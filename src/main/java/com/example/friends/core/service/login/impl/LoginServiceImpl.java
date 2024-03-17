package com.example.friends.core.service.login.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.friends.common.constants.Errors;
import com.example.friends.common.exception.BusinessWarnException;
import com.example.friends.common.utils.StringUtils;
import com.example.friends.core.service.login.LoginService;
import com.example.friends.core.service.user.UserService;
import com.example.friends.core.service.user.bo.UserBO;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private UserService userService;

    @Override
    public String login(String mobile, String password) {
        var user = userService.queryUserByUid(mobile, password);
        if (user == null) {
            throw new BusinessWarnException(Errors.USER_ERROR);
        }
        var uuid = UUID.randomUUID().toString();
        var userJson = JSON.toJSONString(user);
        try (var redis = jedisPool.getResource()) {
            redis.setex(uuid, 3600 * 24 * 7, userJson);
        }
        return uuid;
    }

    @Override
    public UserBO authCheck(String token) {
        try (var redis = jedisPool.getResource()) {
            var user = redis.get(token);
            if (StringUtils.isEmpty(user)) {
                throw new BusinessWarnException(Errors.AUTH_ERROR);
            }
            return JSONObject.parseObject(user, UserBO.class);
        }
    }

    @Override
    public String loginWithoutPassword() {
        var user = userService.createUserWithoutRegister();
        var uuid = UUID.randomUUID().toString();
        var userJson = JSON.toJSONString(user);
        try (var redis = jedisPool.getResource()) {
            redis.setex(uuid, 3600 * 24 * 7, userJson);
        }
        return uuid;
    }
}
