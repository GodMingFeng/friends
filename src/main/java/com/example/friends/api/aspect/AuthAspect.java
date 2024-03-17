package com.example.friends.api.aspect;

import com.example.friends.api.annotations.IgnoreAuth;
import com.example.friends.common.constants.Errors;
import com.example.friends.common.exception.BusinessWarnException;
import com.example.friends.common.utils.HttpUtils;
import com.example.friends.common.utils.StringUtils;
import com.example.friends.common.utils.ThreadLocalHolder;
import com.example.friends.common.utils.UserInfoHolder;
import com.example.friends.core.service.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
public class AuthAspect {

    @Resource
    private LoginService loginService;

    @Around("execution(* com.example.friends.api.controller..*.*(..))")
    public Object aroundAnotherMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            var httpRequest = HttpUtils.getHttpRequest();
            var method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            var ignoreAuth = AnnotationUtils.findAnnotation(method, IgnoreAuth.class);
            if (ignoreAuth == null) {
                var http = HttpUtils.getHttpRequest();
                var token = http.getHeader("access-token");
                if (StringUtils.isEmpty(token)) {
                    throw new BusinessWarnException(Errors.AUTH_ERROR);
                }
                var user = loginService.authCheck(token);
                UserInfoHolder.setUserUuid(user.getUniqueId());
            }
            return joinPoint.proceed();
        } finally {
            ThreadLocalHolder.clear();
        }

    }
}
