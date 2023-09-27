package com.bookservice.gametimebooking.service.house;

import com.bookservice.gametimebooking.controller.house.dto.CreateHouseRequest;
import com.bookservice.gametimebooking.controller.house.dto.CreateHouseResponse;
import com.bookservice.gametimebooking.controller.house.dto.HouseResponse;
import com.bookservice.gametimebooking.controller.house.dto.OverwriteHouseRequest;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.model.Manager;
import com.bookservice.gametimebooking.repository.HouseRepository;
import com.bookservice.gametimebooking.repository.ManagerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HouseService {

    private HouseRepository houseRepository;
    private ManagerRepository managerRepository;

    private HouseMapper houseMapper;

    public CreateHouseResponse createHouse(CreateHouseRequest createHouseRequest) {
        Manager manager = managerRepository.findById(createHouseRequest.getManagerId())
                .orElseThrow(() -> new RuntimeException("Manager not found"));

        House house = houseMapper.toEntity(createHouseRequest);

        manager.addHouse(house);

        houseRepository.save(house);

        return houseMapper.toCreateHouseResponse(house);
    }

    public List<HouseResponse> getAllHouses() {
        return houseRepository.findAll().stream().map(houseMapper::toHouseResponse).collect(Collectors.toList());
    }

    public HouseResponse getHouseById(Long id) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("House not found"));
        return houseMapper.toHouseResponse(house);
    }

    public void overwriteHouseById(Long id, OverwriteHouseRequest request) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("House not found"));

        house.setAddress(request.getAddress());

        houseRepository.save(house);
    }

    public void deleteById(Long id) {
        if (!houseRepository.existsById(id)) {
            throw new RuntimeException("House not found");
        }
        houseRepository.deleteById(id);
    }
}
