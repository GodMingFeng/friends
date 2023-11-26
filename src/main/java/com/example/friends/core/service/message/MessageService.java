package com.example.friends.core.service.message;

import com.example.friends.core.service.message.ao.ReceiveMessageAO;
import com.example.friends.core.service.message.ao.ResponseMessageAO;
import com.example.friends.core.service.message.ao.SendMessageAO;
import com.example.friends.mapper.domain.Message;

public interface MessageService {

    /**
     * 发出漂流瓶
     *
     * @param sendMessageAO 发送漂流瓶
     * @return 是否成功
     */
    Boolean sendMessage(SendMessageAO sendMessageAO);

    /**
     * 收取漂流瓶
     *
     * @param receiveMessageAO 收取漂流瓶
     * @return 结果
     */
    Message receiveMessage(ReceiveMessageAO receiveMessageAO);

    /**
     * 回应漂流瓶
     *
     * @param responseMessageAO 回应漂流瓶
     * @return 结果
     */
    Boolean responseMessage(ResponseMessageAO responseMessageAO);
}
