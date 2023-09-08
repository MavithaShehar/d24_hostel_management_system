package com.example.hibernate.dao;

import com.example.hibernate.dao.custom.impl.RoomDAOImpl;
import com.example.hibernate.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public enum DAOTypes{
        STUDENT, ROOM
    }

    public SuperDAO getDAO(DAOTypes type){
        switch (type){
            case STUDENT: return new StudentDAOImpl();
            case ROOM: return new RoomDAOImpl();
            default: return null;
        }
    }

}


