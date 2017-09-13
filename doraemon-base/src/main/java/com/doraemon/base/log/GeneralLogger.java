package com.doraemon.base.log;

import com.doraemon.base.redis.RedisOperation;

/**
 * 通用的日志记录器
 * Created by zbs on 2017/9/12.
 */
public abstract class GeneralLogger {

    /**
     * redis作为队列
     * @return
     */
    abstract RedisOperation redis();

    /**
     * 发送log
      * @param queueName
     * @param value
     * @throws Exception
     */
    public void sendLog(String queueName,String ... value) throws Exception {
        redis().push(queueName,value);
    }

    /**
     * 处理log
     * @param queueName
     */
    abstract void processing(String queueName);

}
