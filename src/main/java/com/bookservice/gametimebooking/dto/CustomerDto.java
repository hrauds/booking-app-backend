package com.bookservice.gametimebooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String telephone;
}
