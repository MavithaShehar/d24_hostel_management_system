package com.example.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReservationDTO {

    private String res_id;
    private LocalDate date;
    private String status;
    private StudentDTO student;
    private RoomDTO room;

    public ReservationDTO() {}
}
