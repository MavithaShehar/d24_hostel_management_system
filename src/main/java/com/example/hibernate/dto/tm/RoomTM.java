package com.example.hibernate.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomTM {
    private String roomTypeId;
    private String type;
    private String keyMoney;
    private Integer qty;

    public RoomTM(){}
}
