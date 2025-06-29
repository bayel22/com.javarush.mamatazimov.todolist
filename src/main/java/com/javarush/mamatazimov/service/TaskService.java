package com.javarush.mamatazimov.service;

import com.javarush.mamatazimov.dao.TaskDAO;
import com.javarush.mamatazimov.dto.TaskDTO;
import com.javarush.mamatazimov.entity.Status;
import com.javarush.mamatazimov.entity.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    public List<Task> getTasksPaged(int  page, int pageSize) {
        return taskDAO.getTasksPaged(page, pageSize);
    }

    @Transactional(readOnly = true)
    public int getTaskCount() {
        return taskDAO.getTaskCount();
    }

    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        taskDAO.addTask(task);
    }

    public void editTask(int id, TaskDTO taskDTO) {
        Task task = getTask(id);
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        taskDAO.editTask(task);
    }

    public void deleteTask(int id) {
        Task task = getTask(id);
        if(task != null){
            taskDAO.deleteTask(task);
        } else {
            throw new RuntimeException("Task not found with id: " + id);
        }
    }

    @Transactional(readOnly = true)
    public Task getTask(int id) {
        return taskDAO.getTask(id);
    }


}
