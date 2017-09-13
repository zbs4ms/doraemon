package com.doraemon.workflow.example;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zbs on 2017/6/21.
 */
public class MyProcessTest {

//    public static void main(String[] args) {
//        ProcessEngineConfiguration processEngineConfiguration = new StandaloneInMemProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
//                .setJdbcUsername("sa")
//                .setJdbcPassword("")
//                .setJdbcDriver("org.h2.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//
//        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
//
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//
//        repositoryService.createDeployment()
//                .addClasspathResource("MyProcess1.bpmn20.xml")
//                .deploy();
//
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
//        System.out.println("流程名称:"+processDefinition.getKey());
//
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        //设置变量
//        Map<String,Object> variables = new HashMap<String, Object>();
//        variables.put("applyUser","张二娃");  //签收人名称
//        variables.put("days",3); //请假天数
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinition.getKey(),variables);
//
//        TaskService taskService = processEngine.getTaskService();
//        //deptLeader的未签收任务
//        Task taskOfDeptLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
//        System.out.println("未签收任务:"+taskOfDeptLeader.getName());
//        //调用claim方法进行签收,并设定任务归leaderUser所有
//        taskService.claim(taskOfDeptLeader.getId(),"leaderUser");
//        //模拟领导处理,设置变量为true通过
//        variables = new HashMap<String, Object>();
//        variables.put("approved",true);
//        taskService.complete(taskOfDeptLeader.getId(),variables);
//        System.out.println("办理完毕");
//        //为了理解,这次任务已经办理完毕,再查下组deptLeader任务为空
//        taskOfDeptLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
//        //获取历史记录接口
//        HistoryService historyService = processEngine.getHistoryService();
//        long count = historyService.createHistoricProcessInstanceQuery().finished().count();
//        System.out.print("长度"+count);
//
//    }
}
