package com.doraemon.workflow.example;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;


/**
 * Created by zbs on 2017/6/20.
 */
public class OnboardingRequest {
//    public static void main(String[] args) throws ParseException {
//        ProcessEngineConfiguration cfg = new StandaloneInMemProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
//                .setJdbcUsername("sa")
//                .setJdbcPassword("")
//                .setJdbcDriver("org.h2.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        ProcessEngine processEngine = cfg.buildProcessEngine();
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("MyProcess.bpmn")
//                .deploy();
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//                .deploymentId(deployment.getId())
//                .singleResult();
//        System.out.println(
//                "测试中流程节点名["
//                        + processDefinition.getName() + "] with id ["
//                        + processDefinition.getId() + "]");

//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
//        System.out.println(" 哈哈哈哈哈 ["
//                + processInstance.getId()
//                + "] key [" + processInstance.getName() + "]");
//        TaskService taskService = processEngine.getTaskService();
//        FormService formService = processEngine.getFormService();
//        HistoryService historyService = processEngine.getHistoryService();
//
        //       Scanner scanner = new Scanner(System.in);
 //       while (processInstance != null && !processInstance.isEnded()) {
            //         List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
//            System.out.println("Active outstanding tasks: [" + tasks.size() + "]");
//            for (int i = 0; i < tasks.size(); i++) {
//                Task task = tasks.get(i);
//                System.out.println("Processing Task [" + task.getName() + "]");
//                Map<String, Object> variables = new HashMap<String, Object>();
//                FormData formData = formService.getTaskFormData(task.getId());
//                for (FormProperty formProperty : formData.getFormProperties()) {
//                    if (StringFormType.class.isInstance(formProperty.getType())) {
//                        System.out.println(formProperty.getName() + "?");
//                        String value = scanner.nextLine();
//                        variables.put(formProperty.getId(), value);
//                    } else if (LongFormType.class.isInstance(formProperty.getType())) {
//                        System.out.println(formProperty.getName() + "? (Must be a whole number)");
//                        Long value = Long.valueOf(scanner.nextLine());
//                        variables.put(formProperty.getId(), value);
//                    } else if (DateFormType.class.isInstance(formProperty.getType())) {
//                        System.out.println(formProperty.getName() + "? (Must be a date m/d/yy)");
//                        DateFormat dateFormat = new SimpleDateFormat("m/d/yy");
//                        Date value = dateFormat.parse(scanner.nextLine());
//                        variables.put(formProperty.getId(), value);
//                    } else {
//                        System.out.println("<form type not supported>");
//                    }
//                }
//                taskService.complete(task.getId(), variables);
//
//                HistoricActivityInstance endActivity = null;
//                List<HistoricActivityInstance> activities =
//                        historyService.createHistoricActivityInstanceQuery()
//                                .processInstanceId(processInstance.getId()).finished()
//                                .orderByHistoricActivityInstanceEndTime().asc()
//                                .list();
//                for (HistoricActivityInstance activity : activities) {
//                    if (activity.getActivityType() == "startEvent") {
//                        System.out.println("BEGIN " + processDefinition.getName()
//                                + " [" + processInstance.getProcessDefinitionKey()
//                                + "] " + activity.getStartTime());
//                    }
//                    if (activity.getActivityType() == "endEvent") {
//                        // Handle edge case where end step happens so fast that the end step
//                        // and previous step(s) are sorted the same. So, cache the end step
//                        //and display it last to represent the logical sequence.
//                        endActivity = activity;
//                    } else {
//                        System.out.println("-- " + activity.getActivityName()
//                                + " [" + activity.getActivityId() + "] "
//                                + activity.getDurationInMillis() + " ms");
//                    }
//                }
//                if (endActivity != null) {
//                    System.out.println("-- " + endActivity.getActivityName()
//                            + " [" + endActivity.getActivityId() + "] "
//                            + endActivity.getDurationInMillis() + " ms");
//                    System.out.println("COMPLETE " + processDefinition.getName() + " ["
//                            + processInstance.getProcessDefinitionKey() + "] "
//                            + endActivity.getEndTime());
//                }
//            }
//            // Re-query the process instance, making sure the latest state is available
//            processInstance = runtimeService.createProcessInstanceQuery()
//                    .processInstanceId(processInstance.getId()).singleResult();
//        }
//        scanner.close();
//        }

//    }
}
