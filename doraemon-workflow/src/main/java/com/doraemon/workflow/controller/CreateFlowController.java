package com.doraemon.workflow.controller;

import com.alibaba.fastjson.JSONObject;
import com.doraemon.workflow.service.CreateFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zbs on 2017/6/22.
 */
@RestController
@RequestMapping("/create")
@Slf4j
public class CreateFlowController {

    @Autowired
    CreateFlowService createFlowService;

    @RequestMapping(value = "szyd", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject createSzyd(@RequestParam(value = "name") String name) throws Exception {
        createFlowService.createSzyd(name);
        return null;
    }
}
