package com.doraemon.eureka.ribbon.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zbs on 2017/8/25.
 */
@FeignClient("first-eureka-client")
public interface FeignExampleClient {

    @RequestMapping (value ="simple/{id}",method = RequestMethod.GET)  //不能用@GetMapping注解,value指调用的eureka中的请求接口名
    String simple(@PathVariable("id") Long id);  //必须加上("id"),其他地方实际可以不加的

    @RequestMapping (value = "feignPost",method = RequestMethod.POST)
    String post(@RequestBody String name);
}
