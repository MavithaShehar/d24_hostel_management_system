package com.example.hibernate.dao.custom.impl;

import com.example.hibernate.dao.custom.UserDAO;
import com.example.hibernate.entity.User;
import com.example.hibernate.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> getAll() throws Exception {
        return null;
    }

    @Override
    public boolean save(User entity) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(User entity) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean exist(String id) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.remove(session.get(User.class, id));

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public User search(String id) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);

        transaction.commit();
        session.close();

        return user;

    }
}
