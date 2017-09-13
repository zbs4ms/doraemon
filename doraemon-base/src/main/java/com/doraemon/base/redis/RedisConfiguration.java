package com.doraemon.base.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zbs on 2017/6/23.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisConfiguration {

    private int maxTotal = 8;
    private int maxIdle = 8;
    private int maxWaitMillis = 1000 * 10;
    private int minIdle = 0;
    private int port = 6379;
    private String host = null;
    private int timeout = 1000 * 60;
    private String password = null;

}
