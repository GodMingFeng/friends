package com.example.friends.common.utils;

public class UserInfoHolder {

    private static final String USER_HOLDER = "USER_HOLDER";

    public static void setUserUuid(String userUuid) {
        ThreadLocalHolder.set(USER_HOLDER, userUuid);
    }

    public static String getUerUuid() {
        return (String) ThreadLocalHolder.get(USER_HOLDER);
    }
}
