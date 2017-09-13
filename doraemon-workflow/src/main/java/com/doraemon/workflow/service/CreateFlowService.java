package com.doraemon.workflow.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zbs on 2017/6/22.
 */
@Service
public class CreateFlowService {

    @Autowired
    ProcessEngine processEngine;

    public void createSzyd(String name){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .name(name)
                .addClasspathResource("szyd.bpmn")
                .deploy();
    }

    /**
     *
     * @param processInstanceId 流程ID
     * @param assigneeId 办理人ID
     */
    public void setVariables(String processInstanceId,String assigneeId){
        //获取任务的Service，设置和获取流程变量
        TaskService taskService = processEngine.getTaskService();
    }
}
