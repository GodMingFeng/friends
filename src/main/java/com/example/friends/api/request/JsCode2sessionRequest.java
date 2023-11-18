package com.example.friends.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class JsCode2sessionRequest implements Serializable {

    private static final long serialVersionUID = -3697215580560046428L;

    /**
     * jsCode
     */
    private String jsCode;
}
