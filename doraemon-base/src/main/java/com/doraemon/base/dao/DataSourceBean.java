package com.doraemon.base.dao;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;

import java.sql.SQLException;

/**
 * Created by zbs on 2017/9/12.
 */
@Data
public class DataSourceBean {
    String url;
    String username;
    String password;
    static DruidDataSource druidDataSource;

    public void create() {
        if(druidDataSource == null) {
            druidDataSource = new DruidDataSource();
        }
    }

    public DruidDataSource build() throws SQLException {
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setFilters("stat, wall");
        return druidDataSource;
    }
}
