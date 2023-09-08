package com.example.hibernate.bo.custom;

import com.example.hibernate.bo.SuperBO;
import com.example.hibernate.dto.StudentDTO;

import java.util.List;

public interface StudentBO<T> extends SuperBO {

    List<StudentDTO> getAll() throws Exception;

    public boolean save(StudentDTO entity) throws Exception;

    public boolean update(StudentDTO entity)throws Exception;

    public boolean delete(String sId)throws Exception;

    public StudentDTO searchStudent(String id) throws Exception;



}
