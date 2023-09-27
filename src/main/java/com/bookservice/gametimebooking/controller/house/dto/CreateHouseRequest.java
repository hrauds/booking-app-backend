package com.bookservice.gametimebooking.controller.house.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateHouseRequest {

    private String address;

    private Long managerId;
}
