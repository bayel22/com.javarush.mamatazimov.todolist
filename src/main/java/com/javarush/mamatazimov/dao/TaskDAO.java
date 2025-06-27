package com.javarush.mamatazimov.dao;

import com.javarush.mamatazimov.entity.Task;

import java.util.List;

public interface TaskDAO {
    List<Task> getAllTasks();
    Task getTask(int id);
    void addTask(Task task);
    void editTask(Task task);
    void deleteTask(int id);
    List<Task>  getTasksPaged(int  page, int pageSize);
}
