package com.example.friends.api.aspect;

import com.example.friends.common.Response;
import com.example.friends.common.exception.BusinessWarnException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class HttpAdvice {

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public Response<Object> handleException(Throwable e) {
        if (e instanceof BusinessWarnException) {
            var error = ((BusinessWarnException) e).getErrors();
            return Response.error(error);
        }
        log.error("[http请求异常][e={}]", e.getMessage(), e);
        return Response.error();
    }
}
