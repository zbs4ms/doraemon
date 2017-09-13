package com.doraemon.workflow.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zbs on 2017/6/22.
 */

@Configuration
public class WorkflowConfig {

//    @Bean
//    public ProcessEngineConfiguration getProcessEngineConfigurationByH2(){
//        ProcessEngineConfiguration processEngineConfiguration = new StandaloneInMemProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
//                .setJdbcUsername("sa")
//                .setJdbcPassword("")
//                .setJdbcDriver("org.h2.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        return processEngineConfiguration;
//    }

    @Bean
    public ProcessEngineConfiguration getProcessEngineConfigurationByMysql(){
        ProcessEngineConfiguration processEngineConfiguration = new StandaloneInMemProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://192.168.81.10:3306/JSKJ_GZW_WORKFLOW?useUnicode=true&characterEncoding=utf-8&useSSL=false")
                .setJdbcUsername("root")
                .setJdbcPassword("Guoziwei2017%")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        return processEngineConfiguration;
    }

    @Bean
    public ProcessEngine getProcessEngine(ProcessEngineConfiguration processEngineConfiguration){
        return processEngineConfiguration.buildProcessEngine();
    }
}
