package com.example.hibernate.bo.custom;

import com.example.hibernate.bo.SuperBO;
import com.example.hibernate.dto.ReservationDTO;
import com.example.hibernate.dto.RoomDTO;
import com.example.hibernate.dto.StudentDTO;

import java.util.List;

public interface ReservationBO<T> extends SuperBO {

    String setReservationID()throws Exception;

    RoomDTO getRooms(String value)throws Exception;

    List<StudentDTO> getStudents()throws Exception;

    boolean registerStudent(ReservationDTO reservation)throws Exception;

    List<RoomDTO> getAllRooms()throws Exception;

    List<ReservationDTO> getAllDetails()throws Exception;

    ReservationDTO setFields(String text)throws Exception;

    boolean UpdateStudent(ReservationDTO reservation)throws Exception;

}
