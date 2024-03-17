package com.example.friends.api.controller;

import com.example.friends.api.annotations.IgnoreAuth;
import com.example.friends.api.request.LoginWithPasswordRequest;
import com.example.friends.common.Response;
import com.example.friends.common.constants.Errors;
import com.example.friends.common.exception.BusinessWarnException;
import com.example.friends.common.utils.MD5Utils;
import com.example.friends.common.utils.StringUtils;
import com.example.friends.core.service.login.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/friends/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @ResponseBody
    @RequestMapping(path = "/loginWithPassword", method = RequestMethod.POST)
    public Response<String> loginWithPassword(@RequestBody LoginWithPasswordRequest loginWithPasswordRequest) {
        var mobile = loginWithPasswordRequest.getMobile();
        var password = loginWithPasswordRequest.getPassword();
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new BusinessWarnException(Errors.PARAMS_ERROR);
        }
        var token = loginService.login(mobile, MD5Utils.MD5(password));
        return Response.<String>success().result(token);
    }

    @ResponseBody
    @IgnoreAuth
    @RequestMapping(path = "/loginWithoutPassword", method = RequestMethod.POST)
    public Response<String> loginWithoutPassword() {
        var token = loginService.loginWithoutPassword();
        return Response.<String>success().result(token);
    }
}
