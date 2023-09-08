package com.example.hibernate.dao.custom.impl;

import com.example.hibernate.dao.custom.StudentDAO;
import com.example.hibernate.entity.Student;
import com.example.hibernate.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<Student> getAll() throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Student");
        nativeQuery.addEntity(Student.class);
        List<Student> customers = nativeQuery.list();


        transaction.commit();
        session.close();

        return customers;

    }

    @Override
    public boolean save(Student entity) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public boolean update(Student entity) throws Exception {

        Session session =FactoryConfiguration.getInstance().getSession();
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
        session.remove(session.get(Student.class, id));
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String id) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, id);

        transaction.commit();
        session.close();

        return student;
    }
}
