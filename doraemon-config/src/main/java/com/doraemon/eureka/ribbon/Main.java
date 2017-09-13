package com.doraemon.eureka.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import com.doraemon.eureka.ribbon.config.FirstRibbonConfiguration;

/**
 * Created by zbs on 2017/8/25.
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RibbonClient(name = "first-eureka-client", configuration = FirstRibbonConfiguration.class)  //自己加入负载均衡算法
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = ExcludeFromComponentScan.class)})
public class Main {

    @Bean
    @LoadBalanced  //把 restTemplate 变成具有ribbongong'n
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
