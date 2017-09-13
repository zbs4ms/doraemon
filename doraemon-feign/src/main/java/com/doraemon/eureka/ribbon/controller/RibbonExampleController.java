package com.doraemon.eureka.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zbs on 2017/8/25.
 */
@RestController
@RequestMapping("/")

public class RibbonExampleController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    FeignExampleClient feignExampleClient;


    /**
     * 返回本次调取的服务情况
     * @return
     */
    @GetMapping
    public String test(){
        ServiceInstance serviceInstance =  this.loadBalancerClient.choose("first-eureka-client");
        return serviceInstance.getHost()
                +" : "+serviceInstance.getPort()
                +" : "+serviceInstance.getUri()
                +" : "+ serviceInstance.getServiceId();
    }

    @GetMapping("simple/{id}")
    public String findById(@PathVariable Long id){
        return restTemplate.getForObject("http://first-eureka-client/simple/"+id,String.class);
    }

    @GetMapping("feign/{id}")
    public String findById1(@PathVariable Long id){
        return feignExampleClient.simple(id);
    }

    @GetMapping("feignG/{name}")
    public String postUser(@PathVariable String name){
        return feignExampleClient.post(name);
    }
}
