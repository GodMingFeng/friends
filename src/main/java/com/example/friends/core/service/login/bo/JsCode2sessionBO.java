package com.example.friends.core.service.login.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsCode2sessionBO implements Serializable {

    private static final long serialVersionUID = 4350528887914083862L;

    @JSONField(name = "session_key")
    private String sessionKey;

    private String unionid;

    private String errmsg;

    private String openid;

    private String errcode;
}
