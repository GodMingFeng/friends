package com.example.friends.api.controller;

import com.example.friends.api.annotations.IgnoreAuth;
import com.example.friends.common.utils.MessageUtils;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/fake_jy")
public class SendMessageController {

    @ResponseBody
    @IgnoreAuth
    @RequestMapping(path = "/sendMessage", method = RequestMethod.GET)
    public String sendMessageGet(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
        return echostr == null ? "success" : echostr;
    }

    @IgnoreAuth
    @ResponseBody
    @SneakyThrows
    @RequestMapping(path = "/sendMessage", method = RequestMethod.POST, consumes = {MediaType.TEXT_XML_VALUE}, produces = {MediaType.TEXT_XML_VALUE})
    public String sendMessagePost(@RequestParam String timestamp, @RequestParam String nonce) {
        return MessageUtils.WX.encryptMsg("success", timestamp, nonce);
    }
}
