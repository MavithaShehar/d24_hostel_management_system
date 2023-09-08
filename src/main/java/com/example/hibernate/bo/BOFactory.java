package com.example.hibernate.bo;

import com.example.hibernate.bo.custom.impl.LoginBOImpl;
import com.example.hibernate.bo.custom.impl.ReservationBOImpl;
import com.example.hibernate.bo.custom.impl.RoomBOImpl;
import com.example.hibernate.bo.custom.impl.StudentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    public enum BOTypes {
        STUDENT, ROOM, RESERVATION,LOGIN;

    }

    public SuperBO getBO(BOTypes type){
        switch (type){
            case STUDENT: return new StudentBOImpl();
            case ROOM: return new RoomBOImpl();
            case RESERVATION: return new ReservationBOImpl();
            case LOGIN: return new LoginBOImpl();
            default: return null;
        }
    }

}
