package com.example.friends.common.exception;

import com.example.friends.common.constants.Errors;
import lombok.Data;

import java.util.Map;

/**
 * 业务异常.
 */
@Data
public class BusinessErrorException extends RuntimeException {

    private static final long serialVersionUID = -6544264931139416862L;

    private Map<String, Object> ext;

    private Errors errors;

    public BusinessErrorException() {
        super();
    }

    public BusinessErrorException(Errors errors) {
        this.errors = errors;
    }

    public BusinessErrorException(String message) {
        super(message);
    }

    public BusinessErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessErrorException(Throwable cause) {
        super(cause);
    }
}
