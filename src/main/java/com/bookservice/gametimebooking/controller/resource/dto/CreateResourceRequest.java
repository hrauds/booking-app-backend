package com.bookservice.gametimebooking.controller.resource.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateResourceRequest {

    private String resourceName;

    private Long serviceId;
}
