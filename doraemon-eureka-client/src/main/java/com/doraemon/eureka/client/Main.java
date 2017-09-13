package com.doraemon.eureka.client;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zbs on 2017/8/25.
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class Main {

    @Autowired
    EurekaClient eurekaClient;
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/simple/{id}")
    public String findById(@PathVariable Long id){
        return "我是eureka-client: id="+String.valueOf(id);
    }

    @GetMapping("/eureka-instance")
    public String serviceUrl() {
        InstanceInfo instanceInfo = this.eurekaClient.getNextServerFromEureka("FIRST-EUREKA-CLIENT",false);
        return instanceInfo.getHomePageUrl();
    }


    @PostMapping("feignPost")
    public String postUser(@RequestBody String name){
        return "我是  client , post请求中 : name=:"+name;
    }

    @GetMapping("discovery-instance")
    public ServiceInstance discoveryUrl(){
        ServiceInstance serviceInstance = this.discoveryClient.getLocalServiceInstance();
        return serviceInstance;
    }


    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
