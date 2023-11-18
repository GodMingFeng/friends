package com.example.friends.common.utils;

import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Map;

public class HttpUtils {

    private static final CloseableHttpClient CLIENTS = HttpClients.createDefault();

    @SneakyThrows
    public static String post(Map<String, String> headers, String body, String url) {
        var post = new HttpPost(url);
        post.setHeader("Content-type", "application/json");
        for (var entry : headers.entrySet()) {
            post.setHeader(entry.getKey(), entry.getValue());
        }
        post.setEntity(new StringEntity(body));
        var response = CLIENTS.execute(post);
        return EntityUtils.toString(response.getEntity());
    }


    @SneakyThrows
    public static String get(Map<String, String> headers, Map<String, String> params, String url) {
        var get = new HttpGet();
        for (var entry : headers.entrySet()) {
            get.setHeader(entry.getKey(), entry.getValue());
        }
        var paramsToUrl = UrlUtils.appendParamsToUrl(url, params);
        get.setURI(new URI(paramsToUrl));
        var response = CLIENTS.execute(get);
        System.out.println("request: " + paramsToUrl);
        var result = EntityUtils.toString(response.getEntity());
        System.out.println("response: " + result);
        return result;
    }

    /**
     * 获取http request
     */
    public static HttpServletRequest getHttpRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
