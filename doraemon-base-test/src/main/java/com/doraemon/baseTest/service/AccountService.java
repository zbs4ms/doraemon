package com.doraemon.baseTest.service;

import com.doraemon.baseTest.dao.mapper.AccountMapper;
import com.doraemon.baseTest.dao.models.Account;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Created by sloan on 17/5/31.

 */
//指定该service的名称,在注入时,按照该名称进行注入
@Slf4j
@Service("accountService")
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

   // @WriteDataSource
    public Account findAccountIdByCompanyId(Long companyId){
        Account account = accountMapper.findManagerIdByCompanyId(companyId);
        Preconditions.checkNotNull(account,"该企业管理员为空");
        return account;
    }

   // @ReadDataSource
    public PageInfo findAccountAll(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Account> account = accountMapper.selectAll();
        Preconditions.checkNotNull(account,"该企业管理员为空");
        PageInfo page = new PageInfo(account);
        return page;
    }

}
