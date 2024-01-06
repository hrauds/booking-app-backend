package com.bookservice.gametimebooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class BookableDto {

    private Long id;

    private LocalDate date;

    private DayOfWeek dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long resourceId;

    private String serviceName;

    private String resourceName;
}
