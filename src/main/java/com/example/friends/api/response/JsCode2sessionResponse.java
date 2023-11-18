package com.example.friends.api.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsCode2sessionResponse implements Serializable {

    private static final long serialVersionUID = 4350528887914083862L;

    private String loginToken;
}
