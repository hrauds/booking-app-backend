package com.bookservice.gametimebooking.controller.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateServiceRequest {

    private String ServiceName;

    private Long houseId;
}
