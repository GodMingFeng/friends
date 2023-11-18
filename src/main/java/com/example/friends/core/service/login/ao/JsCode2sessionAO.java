package com.example.friends.core.service.login.ao;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class JsCode2sessionAO implements Serializable {

    private static final long serialVersionUID = 4350528887914083862L;

    private String appid;

    private String secret;

    @JSONField(name = "js_code")
    private String jsCode;

    @JSONField(name = "grant_type")
    private String grantType;
}
