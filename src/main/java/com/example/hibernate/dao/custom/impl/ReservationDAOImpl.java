package com.example.hibernate.dao.custom.impl;

import com.example.hibernate.dao.custom.ReservationDAO;
import com.example.hibernate.entity.Reservation;
import com.example.hibernate.utill.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {

    @Override
    public List<Reservation> getAll() throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM Reservation");
        nativeQuery.addEntity(Reservation.class);
        List<Reservation> reservations = nativeQuery.list();

        transaction.commit();
        session.close();

        return reservations;

    }

    @Override
    public boolean save(Reservation entity) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;

    }

    @Override
    public boolean update(Reservation entity) throws Exception {

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

        Reservation reservation = new Reservation();
        reservation.setRes_id(id);
        session.delete(reservation);

        transaction.commit();
        session.close();
        return true;

    }

    @Override
    public Reservation search(String id) throws Exception {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = session.get(Reservation.class, id);

        transaction.commit();
        session.close();
        return reservation;

    }
}
