package com.doraemon.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by zbs on 2017/8/25.
 */
@SpringBootApplication
@EnableConfigServer
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

