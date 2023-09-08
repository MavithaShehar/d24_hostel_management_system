package com.example.hibernate.bo.custom.impl;

import com.example.hibernate.bo.custom.StudentBO;
import com.example.hibernate.dao.DAOFactory;
import com.example.hibernate.dao.custom.StudentDAO;
import com.example.hibernate.dto.StudentDTO;
import com.example.hibernate.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAll() throws Exception {
        List<Student> Students =studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student:Students) {
            studentDTOS.add(new StudentDTO(student.getStudent_id(), student.getName(), student.getAddress(),student.getContact(),student.getDob(),student.getGender()));
        }
        return studentDTOS;
    }


    @Override
    public boolean save(StudentDTO entity) throws Exception{

        return studentDAO.save(new Student(entity.getStudent_id(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getDob(), entity.getGender()));

    }


    @Override
    public boolean update(StudentDTO entity) throws Exception {
        return studentDAO.update(new Student(entity.getStudent_id(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getDob(), entity.getGender()));
    }

    @Override
    public boolean delete(String sId) throws Exception {
        return studentDAO.delete(sId);
    }

    @Override
    public StudentDTO searchStudent(String id) throws Exception {
        Student student = studentDAO.search(id);
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(student.getStudent_id());
        studentDTO.setName(student.getName());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setContact(student.getContact());
        studentDTO.setGender(student.getGender());
        studentDTO.setDob(student.getDob());
        return studentDTO;
    }
}
