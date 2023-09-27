package com.bookservice.gametimebooking.service.resource;

import com.bookservice.gametimebooking.controller.house.dto.CreateHouseRequest;
import com.bookservice.gametimebooking.controller.house.dto.CreateHouseResponse;
import com.bookservice.gametimebooking.controller.house.dto.HouseResponse;
import com.bookservice.gametimebooking.controller.resource.dto.CreateResourceRequest;
import com.bookservice.gametimebooking.controller.resource.dto.CreateResourceResponse;
import com.bookservice.gametimebooking.controller.resource.dto.ResourceResponse;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.model.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResourceMapper {

    private final ModelMapper modelMapper;

    public ResourceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Resource toEntity(CreateResourceRequest createResourceRequest) {
        return modelMapper.map(createResourceRequest, Resource.class);
    }

    public CreateResourceResponse toCreateResourceResponse(Resource resource) {
        return modelMapper.map(resource, CreateResourceResponse.class);
    }

    public ResourceResponse toResourceResponse(Resource resource) {
        return modelMapper.map(resource, ResourceResponse.class);
    }
}
