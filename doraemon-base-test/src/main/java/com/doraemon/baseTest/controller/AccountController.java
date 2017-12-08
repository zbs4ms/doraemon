package com.doraemon.baseTest.controller;

import com.alibaba.fastjson.JSONObject;
import com.doraemon.base.guava.DPreconditions;
import com.doraemon.baseTest.dao.models.Account;
import com.doraemon.baseTest.service.AccountService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by righteyte on 16/6/16.
 */

@RestController
@RequestMapping("/")
@Slf4j
public class AccountController extends MyBaseController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "id", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAccountIdByCompanyId(@RequestParam(value = "accountId",required=false) Long accountId) throws Exception {
        DPreconditions.checkNotNull(accountId,"失败的失败的失败",true);
        Account account = accountService.findAccountIdByCompanyId(accountId);
        return ResponseWrapper().addData(account).addMessage("操作成功！！").ExeSuccess();
    }

    @RequestMapping(value = "all", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject findAll(@RequestParam(value = "pageNum") Integer pageNum,@RequestParam(value = "pageSize")Integer pageSize) throws Exception {
        PageInfo account = accountService.findAccountAll(pageNum,pageSize);
        return ResponseWrapper().addData(account).addMessage("操作成功！！").ExeSuccess();
    }

    @RequestMapping(value="redis", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject redis() throws Exception {
        accountService.redisTest();
        return ResponseWrapper().addMessage("ok").ExeSuccess();
    }

}
