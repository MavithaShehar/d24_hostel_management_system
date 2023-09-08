package com.example.hibernate.bo.custom.impl;

import com.example.hibernate.bo.custom.RoomBO;
import com.example.hibernate.dao.DAOFactory;
import com.example.hibernate.dao.custom.RoomDAO;
import com.example.hibernate.dto.RoomDTO;
import com.example.hibernate.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean save(RoomDTO room) throws Exception {
        return roomDAO.save(new Room(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty(),new ArrayList<>()));
    }

    @Override
    public boolean update(RoomDTO room) throws Exception {
        return roomDAO.save(new Room(room.getRoom_type_id(), room.getType(), room.getKey_money(), room.getQty(),new ArrayList<>()));
    }

    @Override
    public boolean delete(String text) throws Exception {
        return false;
    }

    @Override
    public RoomDTO setFields(String text) throws Exception {
        return null;
    }

    @Override
    public List<RoomDTO> getAll() throws Exception {
        return null;
    }
}
