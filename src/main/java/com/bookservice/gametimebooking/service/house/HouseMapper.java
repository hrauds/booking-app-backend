package com.bookservice.gametimebooking.service.house;

import com.bookservice.gametimebooking.controller.house.dto.CreateHouseRequest;
import com.bookservice.gametimebooking.controller.house.dto.CreateHouseResponse;
import com.bookservice.gametimebooking.controller.house.dto.HouseResponse;
import com.bookservice.gametimebooking.model.House;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {

    private final ModelMapper modelMapper;

    public HouseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public House toEntity(CreateHouseRequest createHouseRequest) {
        return modelMapper.map(createHouseRequest, House.class);
    }

    public CreateHouseResponse toCreateHouseResponse(House house) {
        return modelMapper.map(house, CreateHouseResponse.class);
    }

    public HouseResponse toHouseResponse(House house) {
        return modelMapper.map(house, HouseResponse.class);
    }
}
