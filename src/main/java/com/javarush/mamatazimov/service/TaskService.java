package com.javarush.mamatazimov.service;

import com.javarush.mamatazimov.dao.TaskDAO;
import com.javarush.mamatazimov.entity.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    private final TaskDAO taskDAO;
    public TaskService(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksPaged(int  page, int pageSize) {
        return taskDAO.getTasksPaged(page, pageSize);
    }

    @Transactional(readOnly = true)
    public Task getTask(int id) {
        return taskDAO.getTask(id);
    }

    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    public void editTask(Task task) {
        taskDAO.editTask(task);
    }

    public void deleteTask(int id) {
        taskDAO.deleteTask(id);
    }


}
