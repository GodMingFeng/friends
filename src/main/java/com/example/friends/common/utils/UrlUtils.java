package com.example.friends.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class UrlUtils {

    private static final TypeReference<Map<String, String>> TYPE = new TypeReference<>() {
    };

    /**
     * 将对象转换为Map<String, String>
     *
     * @param object 对象
     * @return 转换后的Map
     */
    public static Map<String, String> objectToMap(Object object) {
        Map<String, String> map = new HashMap<>();
        if (object != null) {
            // 使用fastjson将对象转换为JSON字符串
            String jsonString = JSON.toJSONString(object);
            // 使用fastjson将JSON字符串转换为Map
            map = JSON.parseObject(jsonString, TYPE);
        }
        return map;
    }

    /**
     * 将Map中的参数拼接到URL中
     *
     * @param url    原始URL
     * @param params 参数Map
     * @return 拼接后的URL
     */
    public static String appendParamsToUrl(String url, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            boolean isFirst = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (isFirst) {
                    stringBuilder.append("?");
                    isFirst = false;
                } else {
                    stringBuilder.append("&");
                }
                stringBuilder.append(encode(key)).append("=").append(encode(value));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 对参数进行URL编码
     *
     * @param param 参数值
     * @return 编码后的值
     */
    private static String encode(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return param;
        }
    }
}
