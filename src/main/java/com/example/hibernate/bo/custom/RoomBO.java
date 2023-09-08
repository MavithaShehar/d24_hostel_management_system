package com.example.hibernate.bo.custom;

import com.example.hibernate.bo.SuperBO;
import com.example.hibernate.dto.ReservationDTO;
import com.example.hibernate.dto.RoomDTO;
import com.example.hibernate.dto.StudentDTO;

import java.util.List;

public interface RoomBO extends SuperBO {

    public boolean save(RoomDTO room)throws Exception;

    boolean update(RoomDTO room)throws Exception;

    boolean delete(String text)throws Exception;

    RoomDTO setFields(String text)throws Exception;

    List<RoomDTO> getAll() throws Exception;
}
