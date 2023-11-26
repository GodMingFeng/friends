package com.example.friends.core.converter;

import com.example.friends.common.utils.BeanCopiers;
import com.example.friends.core.service.message.ao.SendMessageAO;
import com.example.friends.mapper.domain.Message;

import java.io.Serializable;
import java.util.UUID;

public class MessageConverter implements Serializable {

    private static final long serialVersionUID = -7780321776342900967L;

    /**
     * 发送消息转do
     *
     * @param sendMessageAO 发消息ao
     * @return 结果
     */
    public static Message sendMessageAO2DO(SendMessageAO sendMessageAO) {
        var message = BeanCopiers.copy(sendMessageAO, Message.class);
        message.setUniqueId(UUID.randomUUID().toString());
        return message;
    }
}
