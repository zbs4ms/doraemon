package com.doraemon.eureka.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
