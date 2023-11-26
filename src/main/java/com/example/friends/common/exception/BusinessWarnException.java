package com.example.friends.common.exception;

import com.example.friends.common.constants.Errors;
import lombok.Data;

import java.util.Map;

/**
 * 业务异常.
 */
@Data
public class BusinessWarnException extends RuntimeException {

    private static final long serialVersionUID = -6544264931139416862L;

    private Errors errors;

    private Map<String, Object> ext;

    public BusinessWarnException() {
        super();
    }

    public BusinessWarnException(Errors errors) {
        this.errors = errors;
    }

    public BusinessWarnException(String message) {
        super(message);
    }

    public BusinessWarnException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessWarnException(Throwable cause) {
        super(cause);
    }
}
