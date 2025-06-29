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
    public Task getTask(int id) {
        return sessionFactory.getCurrentSession().get(Task.class, id);
    }

    @Override
    public void deleteTask(Task task) {
        sessionFactory.getCurrentSession().delete(task);
    }


    @Override
    public List<Task> getTasksPaged(int  page, int pageSize) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Task", Task.class)
                .setFirstResult((page-1)*pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    @Override
    public void addTask(Task task) {
        sessionFactory.getCurrentSession().persist(task);
    }

    @Override
    public void editTask(Task task) {
        sessionFactory.getCurrentSession().merge(task);
    }

    @Override
    public int getTaskCount() {
        Long count = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Task", Long.class)
                .getSingleResult();
        return count.intValue();
    }

}
