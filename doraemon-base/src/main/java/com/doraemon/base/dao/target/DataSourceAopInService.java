package com.doraemon.base.dao.target;

import com.doraemon.base.dao.CustomerContextHolder;
import com.doraemon.base.dao.DataSourceEnum;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * Created by zbs on 2017/9/12.
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
@Log4j
public class DataSourceAopInService implements PriorityOrdered {

    @Before("execution(* com..*.*(..)) "
            + " and @annotation(com.doraemon.base.dao.target.ReadDataSource) ")
    public void setReadDataSourceType() {
        //如果已经开启写事务了，那之后的所有读都从写库读
        if(!DataSourceEnum.write.getType().equals(CustomerContextHolder.getReadOrWrite())){
            CustomerContextHolder.setRead();
        }

    }

    @Before("execution(* com..*.*(..)) "
            + " and @annotation(com.doraemon.base.dao.target.WriteDataSource) ")
    public void setWriteDataSourceType() {
        CustomerContextHolder.setWrite();
    }

    @Override
    public int getOrder() {
        //值越小，越优先执行
        return 0;
    }
}
