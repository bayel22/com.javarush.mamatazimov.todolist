package com.javarush.mamatazimov.dao;

import com.javarush.mamatazimov.entity.Task;

import java.util.List;

public interface TaskDAO {
    Task getTask(int id);
    void addTask(Task task);
    void editTask(Task task);
    void deleteTask(Task task);
    int getTaskCount();
    List<Task>  getTasksPaged(int  page, int pageSize);
}
