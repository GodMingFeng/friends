package com.example.friends.common.utils;

import com.example.friends.common.constants.AuthConstants;
import com.example.friends.common.wx.AesException;
import com.example.friends.common.wx.WXBizMsgCrypt;

public class MessageUtils {

    public static WXBizMsgCrypt WX = null;

    static {
        try {
            WX = new WXBizMsgCrypt(AuthConstants.SEND_MESSAGE_TOKEN, AuthConstants.AES_TOKEN, AuthConstants.APP_ID);
        } catch (AesException e) {
            e.printStackTrace();
        }
    }
}
