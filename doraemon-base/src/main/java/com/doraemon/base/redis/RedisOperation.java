package com.doraemon.base.redis;

import com.doraemon.base.guava.DPreconditions;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

/**
 * Created by zbs on 2017/6/23.
 */
@Configuration
@Data
@Log4j
public class RedisOperation {

    @Autowired
    private RedisConfiguration redisConfiguration;

    @Autowired
    private JedisPool jedisPool;

    private boolean usePool = false;

    private boolean usePassword = false;

    public RedisOperation usePool() {
        usePool = true;
        return this;
    }

    public RedisOperation usePassword() {
        usePassword = true;
        return this;
    }

    private Jedis getJedis() throws Exception {
        if(jedisPool == null)
            throw new Exception("redis连接为空.");
        Jedis jedis;
        if (usePool) {
            jedis = jedisPool.getResource();
        }else {
            jedis = new Jedis(redisConfiguration.getHost(), redisConfiguration.getPort());
        }
        log.debug("redis的连接为: ip:" + redisConfiguration.getHost() +
                " host:" + redisConfiguration.getPort() );
        if (usePassword || redisConfiguration.getPassword()!=null) {
            jedis.auth(redisConfiguration.getPassword());
            log.debug(" redis的密码是:password:" + redisConfiguration.getPassword());
        }
        return jedis;
    }

    private void close(Jedis jedis) {
        if (jedis != null)
              jedis.close();
    }

    /**
     * redis 的 get 方法
     *
     * @param key
     * @return
     */
    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            log.info("redis查询 key:"+key);
            DPreconditions.checkNotNullAndEmpty(key,"传入redis查询的值不能为空,或者为''");
            return jedis.get(key);
        } finally {
            this.close(jedis);
        }
    }

    /**
     * redis 的 set 方法
     * @param key
     * @return
     */
    public String set(String key,String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            log.info("redis插入值 key:"+key+" value:"+value);
            return jedis.set(key,value);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * redis 的 delete 方法
     * @param key
     * @return
     */
    public Long del(String key) throws Exception {
        Jedis jedis = null;
        try{
            jedis = getJedis();
            return jedis.del(key);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * redis 设置key 过期时间方法
     * @param key
     * @return
     */
    public void expire(String key,int time) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.expire(key,time);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * redis 自增
     * @param key
     * @return
     */
    public Long incrBy(String key,long value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.incrBy(key,value);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * redis 自减
     * @param key
     * @return
     */
    public Long decrBy(String key,long value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.decrBy(key,value);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * redis 的 执行 lua 脚本
     * @param script
     * @param keys
     * @param args
     * @return
     */
    public Object eval(String script, List<String> keys, List<String> args) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.eval(script,keys,args);
        }finally {
            this.close(jedis);
        }
    }


    /**
     * redis 入队列
     * @param key
     * @param value
     */
    public void push(String key,String ... value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.lpush(key,value);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * redis 出队列
     * @param key
     * @return
     */
    public String pop(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.rpop(key);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * redis 退回一条数据到队列中
     * @param key
     * @return
     */
    public void backToQueue(String key,String valus) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.rpush(key,valus);
        }finally {
            this.close(jedis);
        }
    }

    /**
     * 查询队列 (start = 0 & end = -1  代表查询全部)
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key, long start, long end) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.lrange(key,start,end);
        }finally {
            this.close(jedis);
        }
    }

    /**
    * 封装redis 的hmset方法
    * @param key
    */
    public Map hget(String key ) throws Exception {
        Jedis jedis = null;
        Map<String,String> map;
        try {
            jedis = getJedis();
            return jedis.hgetAll(key);
        } finally {
            this.close(jedis);
        }
    }

    /**
     * 封装redis 的hmset方法
     * @param key
     * @param map
     */
    public void hset(String key ,Map map) throws Exception {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.hmset(key,map);
        } finally {
            this.close(jedis);
        }
    }


}
