package com.example.friends.core.service.user.impl;

import com.example.friends.common.enums.GenderEnums;
import com.example.friends.common.utils.BeanCopiers;
import com.example.friends.common.utils.MD5Utils;
import com.example.friends.core.service.user.UserService;
import com.example.friends.core.service.user.ao.UserSaveAO;
import com.example.friends.core.service.user.bo.UserBO;
import com.example.friends.mapper.domain.User;
import com.example.friends.mapper.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean saveUser(UserSaveAO userSaveAO) {
        var user = BeanCopiers.copy(userSaveAO, User.class);
        user.setUniqueId(UUID.randomUUID().toString());
        userMapper.insertSelective(user);
        return true;
    }

    @Override
    public UserBO queryUserByUid(String uniqueId) {
        var example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("uniqueId", uniqueId);
        var user = userMapper.selectOneByExample(example);
        return user == null ? null : BeanCopiers.copy(user, UserBO.class);
    }

    @Override
    public UserBO queryUserByUid(String mobile, String password) {
        var example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("mobile", mobile)
                .andEqualTo("password", password);
        var user = userMapper.selectOneByExample(example);
        return user == null ? null : BeanCopiers.copy(user, UserBO.class);
    }

    @Override
    public UserBO createUserWithoutRegister() {
        var user = createDefaultUser();
        userMapper.insertSelective(user);
        return BeanCopiers.copy(user, UserBO.class);
    }

    /**
     * 创建默认用户
     *
     * @return 用户
     */
    private User createDefaultUser() {
        var user = new User();
        var random = new Random();
        var count = random.nextInt(10000);
        user.setNickName(String.format("峰神的第%d用户", count));
        user.setUniqueId(UUID.randomUUID().toString());
        user.setPassword(MD5Utils.MD5(UUID.randomUUID().toString()));
        user.setGender(GenderEnums.MALE.getGender());
        return user;
    }
}
