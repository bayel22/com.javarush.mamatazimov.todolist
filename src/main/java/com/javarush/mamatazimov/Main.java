package com.javarush.mamatazimov;

import com.javarush.mamatazimov.config.AppConfig;
import com.javarush.mamatazimov.entity.Status;
import com.javarush.mamatazimov.entity.Task;
import com.javarush.mamatazimov.service.TaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        try(context) {
            TaskService taskService = context.getBean(TaskService.class);
            System.out.println();
            System.out.println(taskService.getAllTasks());
            System.out.println();

            Task task = new Task();
            task.setDescription("ПРОВЕРКА");
            task.setStatus(Status.DONE);

            taskService.saveOrUpdateTask(task);

            taskService.deleteTask(1);

            Task task1 = taskService.getTask(62);
            System.out.println(task1);
        }
    }

}