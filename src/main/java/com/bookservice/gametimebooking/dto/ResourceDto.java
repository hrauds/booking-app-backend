package com.bookservice.gametimebooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceDto {

    private Long id;

    private String resourceName;

    private Long serviceId;
}
