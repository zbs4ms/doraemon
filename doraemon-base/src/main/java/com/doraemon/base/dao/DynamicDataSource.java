package com.doraemon.base.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by zbs on 2017/9/12.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource =  CustomerContextHolder.getReadOrWrite();
        CustomerContextHolder.clear();
        return dataSource;
    }

}
