package com.example.hibernate.bo.custom;

import java.util.List;

public interface ReservationBO<T> extends StudentBO{

    public List<T> getAllReservations();

    public boolean addReservation(T entity);

    public boolean updateReservation(T entity);

    public String generateNewReservationID();

    public boolean deleteReservation(String id);

    public T searchReservation(String id);
}