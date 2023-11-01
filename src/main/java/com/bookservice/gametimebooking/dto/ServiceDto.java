package com.bookservice.gametimebooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceDto {

    private Long id;

    private String serviceName;

    private String houseId;
}
