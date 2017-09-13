package com.doraemon.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * Created by zbs on 2017/6/14.
 */
@SpringBootApplication
@EnableAuthorizationServer
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
