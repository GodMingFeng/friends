package com.example.friends.common.utils;

public class UserInfoHolder {

    private static final String USER_HOLDER = "USER_HOLDER";

    public static void setOpenId(String openId) {
        ThreadLocalHolder.set(USER_HOLDER, openId);
    }

    public static String getOpenId() {
        return (String) ThreadLocalHolder.get(USER_HOLDER);
    }
}
