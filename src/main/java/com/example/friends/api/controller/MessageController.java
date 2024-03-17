package com.example.friends.api.controller;

import com.example.friends.api.request.ReceiveMessageRequest;
import com.example.friends.api.request.ResponseMessageRequest;
import com.example.friends.api.request.SendMessageRequest;
import com.example.friends.common.Response;
import com.example.friends.common.utils.BeanCopiers;
import com.example.friends.common.utils.UserInfoHolder;
import com.example.friends.core.service.message.MessageService;
import com.example.friends.core.service.message.ao.ReceiveMessageAO;
import com.example.friends.core.service.message.ao.SendMessageAO;
import com.example.friends.mapper.domain.Message;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/friends/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @ResponseBody
    @RequestMapping(path = "/sendMessage", method = RequestMethod.POST)
    public Response<Boolean> sendMessage(@RequestBody SendMessageRequest sendMessageRequest) {
        var ao = BeanCopiers.copy(sendMessageRequest, SendMessageAO.class);
        var userUuid = UserInfoHolder.getUerUuid();
        ao.setUserUniqueId(userUuid);
        messageService.sendMessage(ao);
        return Response.<Boolean>success().result(true);
    }

    @ResponseBody
    @RequestMapping(path = "/receiveMessage", method = RequestMethod.POST)
    public Response<Message> receiveMessage(@RequestBody ReceiveMessageRequest receiveMessageRequest) {
        var ao = BeanCopiers.copy(receiveMessageRequest, ReceiveMessageAO.class);
        var userUuid = UserInfoHolder.getUerUuid();
        ao.setUserUniqueId(userUuid);
        var result = messageService.receiveMessage(ao);
        return Response.<Message>success().result(result);
    }

    @ResponseBody
    @RequestMapping(path = "/responseMessage", method = RequestMethod.GET)
    public String responseMessage(@RequestBody ResponseMessageRequest responseMessageRequest) {
        var user = UserInfoHolder.getUerUuid();
        return null;
    }
}
