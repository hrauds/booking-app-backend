package com.bookservice.gametimebooking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto {

    private Integer code;

    private String errorMessage;
}
