package com.example.friends.core.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value("${spring.redis.pw}")
    private String password;

    @Bean
    public JedisPool jedisPool() {
        var poolConfig = new JedisPoolConfig();
        // 配置连接池参数
        return new JedisPool(poolConfig, redisHost, redisPort,3000,password);
    }
}