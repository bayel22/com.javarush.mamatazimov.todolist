package com.javarush.mamatazimov.dao;

import com.javarush.mamatazimov.entity.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOImpl implements TaskDAO {
    private final SessionFactory sessionFactory;

    public TaskDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Task> getAllTasks() {
        return sessionFactory.getCurrentSession().createQuery("from Task", Task.class).list();
    }

    @Override
    public Task getTask(int id) {
        return sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    public void saveOrUpdateTask(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }

    @Override
    public void deleteTask(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);
        if(task != null){
            session.delete(task);
        }
    }

    @Override
    public List<Task> getTasksPaged(int  page, int pageSize) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Task", Task.class)
                .setFirstResult((page-1)*pageSize)
                .setMaxResults(pageSize)
                .list();
    }

}
