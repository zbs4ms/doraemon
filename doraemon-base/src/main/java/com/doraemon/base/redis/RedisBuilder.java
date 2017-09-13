package com.doraemon.base.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by zbs on 2017/6/23.
 */
@Configuration
public class RedisBuilder {

    @Autowired
    RedisConfiguration redisProperties;

    @Bean
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWaitMillis());
        jedisPoolConfig.setMinEvictableIdleTimeMillis(redisProperties.getTimeout());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool getJedisPool(JedisPoolConfig jedisPoolConfig){
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        JedisPool jedisPool =  new JedisPool(jedisPoolConfig,host,port);
        return jedisPool;
    }
}
