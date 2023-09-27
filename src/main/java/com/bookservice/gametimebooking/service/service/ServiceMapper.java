package com.bookservice.gametimebooking.service.service;

import com.bookservice.gametimebooking.controller.house.dto.CreateHouseRequest;
import com.bookservice.gametimebooking.controller.house.dto.CreateHouseResponse;
import com.bookservice.gametimebooking.controller.house.dto.HouseResponse;
import com.bookservice.gametimebooking.controller.service.dto.CreateServiceRequest;
import com.bookservice.gametimebooking.controller.service.dto.CreateServiceResponse;
import com.bookservice.gametimebooking.controller.service.dto.ServiceResponse;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.model.Service;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {

    private final ModelMapper modelMapper;

    public ServiceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Service toEntity(CreateServiceRequest createServiceRequest) {
        return modelMapper.map(createServiceRequest, Service.class);
    }

    public CreateServiceResponse toCreateServiceResponse(Service service) {
        return modelMapper.map(service, CreateServiceResponse.class);
    }

    public ServiceResponse toServiceResponse(Service service) {
        return modelMapper.map(service, ServiceResponse.class);
    }
}
