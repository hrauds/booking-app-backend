//package com.bookservice.gametimebooking.service;
//
//import com.bookservice.gametimebooking.dto.HouseDto;
//import com.bookservice.gametimebooking.mapper.HouseMapper;
//import com.bookservice.gametimebooking.model.House;
//import com.bookservice.gametimebooking.model.Company;
//import com.bookservice.gametimebooking.repository.HouseRepository;
//import com.bookservice.gametimebooking.repository.CompanyRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//@Transactional
//public class HouseService {
//
//    private HouseRepository houseRepository;
//    private CompanyRepository companyRepository;
//
//    private HouseMapper houseMapper;
//
//    public HouseDto createHouse(HouseDto houseDto) {
//        Company company = companyRepository.findById(houseDto.getCompanyId())
//                .orElseThrow(() -> new RuntimeException("Company not found"));
//
//        House house = houseMapper.toEntity(houseDto);
//
//        company.addHouse(house);
//
//        houseRepository.save(house);
//
//        return houseMapper.toDto(house);
//    }
//
//    public List<HouseDto> getAllHouses() {
//        return houseRepository.findAll().stream().map(houseMapper::toDto).collect(Collectors.toList());
//    }
//
//    public HouseDto getHouseById(Long id) {
//        House house = houseRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("House not found"));
//        return houseMapper.toDto(house);
//    }
//
//    public void overwriteHouseById(Long id, HouseDto houseDto) {
//        House house = houseRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("House not found"));
//
//        house.setAddress(houseDto.getAddress());
//
//        houseRepository.save(house);
//    }
//
//    public void deleteById(Long id) {
//        if (!houseRepository.existsById(id)) {
//            throw new RuntimeException("House not found");
//        }
//        houseRepository.deleteById(id);
//    }
//}
