package com.bookservice.gametimebooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDto {

    private Long id;

    private String startTime;

    private String endTime;

    private Long resourceId;

    private String serviceName;

    private String address;

}
