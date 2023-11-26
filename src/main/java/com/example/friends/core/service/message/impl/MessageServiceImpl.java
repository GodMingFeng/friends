package com.example.friends.core.service.message.impl;

import com.example.friends.common.constants.BizConstants;
import com.example.friends.common.constants.Errors;
import com.example.friends.common.enums.MessageStatus;
import com.example.friends.common.exception.BusinessWarnException;
import com.example.friends.common.utils.DateUtils;
import com.example.friends.core.converter.MessageConverter;
import com.example.friends.core.service.message.MessageService;
import com.example.friends.core.service.message.ao.ReceiveMessageAO;
import com.example.friends.core.service.message.ao.ResponseMessageAO;
import com.example.friends.core.service.message.ao.SendMessageAO;
import com.example.friends.mapper.domain.Message;
import com.example.friends.mapper.repository.MessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Resource
    private JedisPool jedisPool;

    /**
     * 用户收取次数统计
     * 1.用户id
     * 2.日期
     */
    private static final String USER_RECEIVE_COUNT_FORMAT = "receive_%s_%s";

    /**
     * 消息被收取次数统计
     */
    private static final String MESSAGE_RECEIVE_COUNT_FORMAT = "message_receive_%S";

    /**
     * 用户已收取消息集合
     */
    private static final String USER_RECEIVE_SET = "receive_set_%s";

    /**
     * 仅收取3小时以内的消息
     */
    private static final Integer EFFECTIVE_HOUR = 3;

    @Override
    public Boolean sendMessage(SendMessageAO sendMessageAO) {
        var message = MessageConverter.sendMessageAO2DO(sendMessageAO);
        messageMapper.insertSelective(message);
        return true;
    }

    @Override
    public Message receiveMessage(ReceiveMessageAO receiveMessageAO) {
        var userUuid = receiveMessageAO.getUserUniqueId();
        var now = new Date();
        try (var redis = jedisPool.getResource()) {
            //判断用户获取次数
            var userCount = getUserReceiveCountKey(userUuid, now, redis);
            if (userCount >= BizConstants.USER_RECEIVE_LIMIT) {
                throw new BusinessWarnException(Errors.RECEIVE_LIMIT);
            }
            //查看用户已获取漂流瓶
            var receiveSet = getUserReceiveSet(userUuid, now, redis);
            var message = queryMessage(receiveSet, receiveMessageAO.getGender(), now);
            if (message == null) {
                throw new BusinessWarnException(Errors.NO_MESSAGE);
            }
            //增加用户已获取漂流瓶
            addUserReceiveSet(userUuid, message.getUniqueId(), now, redis);
            //收取超过10次以后置为失效
            var messageCount = getMessageReceiveCountKey(message.getUniqueId(), redis);
            if (messageCount >= BizConstants.MESSAGE_RECEIVE_LIMIT) {
                updateMessageStatus(message, MessageStatus.DISABLE);
            } else if (Objects.equals(message.getStatus(), MessageStatus.SEND.getStatus())) {
                updateMessageStatus(message, MessageStatus.RECEIVE);
            }
            return message;
        }
    }

    @Override
    public Boolean responseMessage(ResponseMessageAO responseMessageAO) {
        return null;
    }

    /**
     * 获取消息
     *
     * @param receiveSet 已读
     * @param now        当前时间
     * @return 结果
     */
    private Message queryMessage(Set<String> receiveSet, Integer gender, Date now) {
        var example = new Example(Message.class);
        example.createCriteria()
                .andNotEqualTo("status", MessageStatus.DISABLE.getStatus())
                .andEqualTo("gender", gender == 0 ? 1 : 0)
                .andGreaterThanOrEqualTo("gmtCreate", DateUtils.minusHours(now, EFFECTIVE_HOUR));
        if (!CollectionUtils.isEmpty(receiveSet)) {
            example.and().andNotIn("uniqueId", receiveSet);
        }
        example.setOrderByClause("gmt_create asc");
        return messageMapper.selectOneByExample(example);
    }

    /**
     * 获取用户收取消息次数key
     *
     * @param userUniqueId
     * @return
     */
    private Integer getUserReceiveCountKey(String userUniqueId, Date now, Jedis redis) {
        //凌晨2点进行更新
        var dateFormat = DateUtils.formatWithDatePattern(now);
        var zeroDate = DateUtils.setHour(now, 0);
        var updateDate = DateUtils.setHour(now, 2);
        if (!DateUtils.firstIsBeforeSecond(now, zeroDate) && DateUtils.firstIsBeforeSecond(now, updateDate)) {
            dateFormat = DateUtils.formatWithDatePattern(DateUtils.plusDays(now, 1));
        }
        var key = String.format(USER_RECEIVE_COUNT_FORMAT, userUniqueId, dateFormat);
        var result = redis.incr(key);
        if (result == 1L) {
            redis.expire(key, 86400);
        }
        return result.intValue();
    }

    /**
     * 获取消息被收取次数key
     */
    private Integer getMessageReceiveCountKey(String messageUniqueId, Jedis redis) {
        var key = String.format(MESSAGE_RECEIVE_COUNT_FORMAT, messageUniqueId);
        var result = redis.incr(key);
        if (result == 1L) {
            redis.expire(key, 86400);
        }
        return result.intValue();
    }

    private Set<String> getUserReceiveSet(String userUniqueId, Date now, Jedis redis) {
        var key = String.format(USER_RECEIVE_SET, userUniqueId);
        var endTime = DateUtils.minusHours(now, EFFECTIVE_HOUR + 1);
        redis.zremrangeByScore(key, 0, (double) endTime.getTime());
        return redis.zrange(key, 0, now.getTime());
    }

    private void addUserReceiveSet(String userUniqueId, String messageUniqueId, Date now, Jedis redis) {
        var key = String.format(USER_RECEIVE_SET, userUniqueId);
        redis.zadd(key, now.getTime(), messageUniqueId);
    }

    private void updateMessageStatus(Message message, MessageStatus messageStatus) {
        var update = new Message();
        update.setStatus(messageStatus.getStatus());
        var example = new Example(Message.class);
        example.createCriteria()
                .andEqualTo("uniqueId", message.getUniqueId());
        messageMapper.updateByExampleSelective(update, example);
    }
}
