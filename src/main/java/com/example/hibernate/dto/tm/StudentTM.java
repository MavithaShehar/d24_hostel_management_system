package com.example.hibernate.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class StudentTM {
    private String student_id;
    private String name;
    private String address;
    private String contact;
    private LocalDate dob;
    private String gender;

    public StudentTM(){}

}
