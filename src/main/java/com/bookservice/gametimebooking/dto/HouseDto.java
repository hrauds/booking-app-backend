package com.bookservice.gametimebooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HouseDto {

    private Long id;

    private String address;

    private Long companyId;
}
