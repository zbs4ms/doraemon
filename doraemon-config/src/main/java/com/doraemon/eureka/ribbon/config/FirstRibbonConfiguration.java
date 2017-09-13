package com.doraemon.eureka.ribbon.config;

import com.doraemon.eureka.ribbon.ExcludeFromComponentScan;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 不能放到 application main 的上下文中,也就是不能被main扫描到
 * @SpringBootApplication 扫描的范围是他和他下面的子包
 * Created by zbs on 2017/8/25.
 */
@Configuration
@ExcludeFromComponentScan
public class FirstRibbonConfiguration {

    @Autowired
    IClientConfig iClientConfig;

    @Bean
    public IRule ribbonRule(IClientConfig clientConfig){
        return new RandomRule();   //随机分配
    }
}
