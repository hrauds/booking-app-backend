package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.HouseDto;
import com.bookservice.gametimebooking.exceptions.HouseNotFoundException;
import com.bookservice.gametimebooking.mapper.HouseMapper;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.model.Company;
import com.bookservice.gametimebooking.repository.HouseRepository;
import com.bookservice.gametimebooking.repository.CompanyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class HouseService {

    private HouseRepository houseRepository;
    private CompanyRepository companyRepository;
    private HouseMapper houseMapper;
    private static final String HOUSE_NOT_FOUND_ERROR_MESSAGE = "House not found";
    private final GeocodingService geocodingService;

    public HouseDto createHouse(HouseDto houseDto) {
        Company company = companyRepository.findById(houseDto.getCompanyId())
                .orElseThrow(() -> new HouseNotFoundException(HOUSE_NOT_FOUND_ERROR_MESSAGE));

        House house = houseMapper.toEntity(houseDto);

        company.addHouse(house);

        houseRepository.save(house);

        return houseMapper.toDto(house);
    }

    public List<HouseDto> getAllHouses() {
        return houseRepository.findAll().stream()
                .map(houseMapper::toDto)
                .toList();
    }

    public HouseDto getHouseById(Long id) throws JsonProcessingException {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new HouseNotFoundException(HOUSE_NOT_FOUND_ERROR_MESSAGE));
        HouseDto houseDto = houseMapper.toDto(house);
        houseDto.setAddress(houseDto.getAddress() + " " + geocodingService.getCoordinates(house.getAddress()));
        return houseDto;
    }

    public void overwriteHouseById(Long id, HouseDto houseDto) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new HouseNotFoundException(HOUSE_NOT_FOUND_ERROR_MESSAGE));

        house.setAddress(houseDto.getAddress());

        houseRepository.save(house);
    }

    public void deleteById(Long id) {
        if (!houseRepository.existsById(id)) {
            throw new HouseNotFoundException(HOUSE_NOT_FOUND_ERROR_MESSAGE);
        }
        houseRepository.deleteById(id);
    }
}
