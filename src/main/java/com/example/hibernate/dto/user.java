package com.example.hibernate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
 class UserDTO {
    private String id;
    private String username;
    private String password;

    public UserDTO(){}
}

