package com.doraemon.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zbs on 2017/12/8.
 */
@RestController
@RequestMapping("/monitoring")
@Slf4j
public class MonitoringController {

    @RequestMapping(value = "heartbeat", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "ok";
    }
}
