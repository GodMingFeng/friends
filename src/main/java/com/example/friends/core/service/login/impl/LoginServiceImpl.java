package com.example.friends.core.service.login.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.friends.common.constants.AuthConstants;
import com.example.friends.common.utils.BeanCopiers;
import com.example.friends.common.utils.HttpUtils;
import com.example.friends.common.utils.UrlUtils;
import com.example.friends.core.service.login.LoginService;
import com.example.friends.core.service.login.ao.JsCode2sessionAO;
import com.example.friends.core.service.login.bo.JsCode2sessionBO;
import com.example.friends.core.service.login.bo.UserBO;
import com.example.friends.mapper.domain.User;
import com.example.friends.mapper.repository.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private JedisPool jedisPool;

    private static final Integer EXPIRE_TIMES = 86400 * 7;

    @Override
    public String login(JsCode2sessionAO request) {
        var uuid = UUID.randomUUID().toString();
        var result = HttpUtils.get(new HashMap<>(), UrlUtils.objectToMap(request), AuthConstants.JS_CODE_2_SESSION_URL);
        var response = JSONObject.parseObject(result, JsCode2sessionBO.class);
        if (response.getErrmsg() != null || StringUtils.isEmpty(response.getOpenid())) {
            throw new RuntimeException(response.getErrmsg());
        }
        var user = getUserByOpenId(response.getOpenid());
        if (user == null) {
            createUser(response.getOpenid(), response.getUnionid(), response.getSessionKey());
        } else {
            updateUser(response.getOpenid(), response.getUnionid(), response.getSessionKey());
        }
        try (var jedis = jedisPool.getResource()) {
            var oldUuid = jedis.get(response.getOpenid());
            if (!StringUtils.isEmpty(oldUuid)) {
                jedis.del(oldUuid);
            }
            jedis.setex(response.getOpenid(), EXPIRE_TIMES, uuid);
            jedis.setex(uuid, EXPIRE_TIMES, response.getOpenid());
        }
        return uuid;
    }

    @Override
    public UserBO getUserByOpenId(String openId) {
        var example = new Example(User.class);
        example.createCriteria().andEqualTo("openId", openId);
        var user = userMapper.selectOneByExample(example);
        if (user != null) {
            return BeanCopiers.copy(user, UserBO.class);
        }
        return null;
    }

    /**
     * 创建user
     */
    public void createUser(String openId, String unionId, String sessionKey) {
        var user = new User();
        user.setOpenId(openId);
        user.setUnionId(unionId);
        user.setSessionKey(sessionKey);
        userMapper.insertSelective(user);
    }

    public void updateUser(String openId, String unionId, String sessionKey) {
        var example = new Example(User.class);
        example.createCriteria().andEqualTo("openId", openId);
        var user = new User();
        user.setUnionId(unionId);
        user.setSessionKey(sessionKey);
        userMapper.updateByExampleSelective(user, example);
    }
}
