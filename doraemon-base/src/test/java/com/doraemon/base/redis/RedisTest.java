package com.doraemon.base.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zbs on 2017/6/5.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Main.class)
public class RedisTest {

    @Autowired
    RedisOperation redisOperation;

    @Test
    public void redis() throws Exception {
        RedisOperation redisOperation = new RedisOperation();

        redisOperation.usePool().get("aaa");
        redisOperation.usePool().set("aaa","bbbb");
        System.out.println("aaa = " + redisOperation.get("aaa"));
    }
}
