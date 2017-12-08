package com.doraemon.base.dao;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.log4j.Log4j;
import org.ho.yaml.Yaml;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zbs on 16/6/28.
 */
@Configuration
@Log4j
public class SbmMyBatisConfig {

    private Class<? extends DataSource> datasourceType = com.alibaba.druid.pool.DruidDataSource.class;

    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() throws SQLException {
        return new DruidDataSource();
    }

    @Bean(name = "slave1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public DataSource slave1DataSource() throws SQLException {
        return  new DruidDataSource();
    }


    @Bean
    public AbstractRoutingDataSource roundRobinDataSouceProxy(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slave1DataSource") DataSource slave1DataSource){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object,Object> map = new HashMap<>();
        map.put(DataSourceEnum.write.getType(),masterDataSource);
        map.put(DataSourceEnum.read.getType(),slave1DataSource);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        return dynamicDataSource;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() throws Exception {
        System.out.println("[*生成*]MapperScannerConfigurer -> mapperPath:" + getBasePackage());
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(getBasePackage());
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.doraemon.base.dao.base.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    @Bean
    private String getBasePackage() throws Exception {
        InputStream ips = SbmMyBatisConfig.class.getResourceAsStream("/application.yml");
        Map<String,Object> map = Yaml.loadType(ips, HashMap.class);
        Map<String,Object> mybatisObject = map.get("spring") == null ? null :(Map)map.get("spring") ;
        if(mybatisObject == null)
            return null;
        Map<String,String> myObject = mybatisObject.get("datasource") == null ? null : (Map)mybatisObject.get("datasource");
        if(myObject == null)
            return null;
        return myObject.get("mapperPath");
    }

}
