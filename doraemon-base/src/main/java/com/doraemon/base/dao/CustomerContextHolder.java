package com.doraemon.base.dao;

import lombok.extern.log4j.Log4j;

/**
 * Created by zbs on 2017/9/12.
 */
@Log4j
public class CustomerContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 读库
     */
    public static void setRead() {
        contextHolder.set(DataSourceEnum.read.getType());
        log.info("数据库切换到读库...");
    }

    /**
     * 写库
     */
    public static void setWrite() {
        contextHolder.set(DataSourceEnum.write.getType());
        log.info("数据库切换到写库...");
    }

    public static String getReadOrWrite() {
        return contextHolder.get();
    }
    public static void clear() {
        contextHolder.remove();
    }
}
