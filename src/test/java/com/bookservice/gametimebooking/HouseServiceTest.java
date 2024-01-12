package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.HouseDto;
import com.bookservice.gametimebooking.mapper.HouseMapper;
import com.bookservice.gametimebooking.mapper.HouseMapperImpl;
import com.bookservice.gametimebooking.model.Company;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.repository.CompanyRepository;
import com.bookservice.gametimebooking.repository.HouseRepository;
import com.bookservice.gametimebooking.service.GeocodingService;
import com.bookservice.gametimebooking.service.HouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HouseServiceTest {

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Spy
    private HouseMapper houseMapper = new HouseMapperImpl();

    @InjectMocks
    private HouseService houseService;

    @Mock
    private GeocodingService geocodingService;

    @Test
    void createHouse() {
        HouseDto houseDto = new HouseDto();
        houseDto.setCompanyId(1L);
        Company mockCompany = new Company();
        when(companyRepository.findById(houseDto.getCompanyId())).thenReturn(Optional.of(mockCompany));
        House mockHouse = new House();
        when(houseMapper.toEntity(houseDto)).thenReturn(mockHouse);
        when(houseRepository.save(any(House.class))).thenReturn(mockHouse);

        HouseDto result = houseService.createHouse(houseDto);

        verify(companyRepository).findById(houseDto.getCompanyId());
        verify(houseMapper).toEntity(houseDto);
        verify(houseRepository).save(any(House.class));
        assertNotNull(result);
        assertTrue(mockCompany.getHouses().contains(mockHouse));
    }

    @Test
    void getAllHouses() {
        House house1 = new House();
        House house2 = new House();
        List<House> houseList = Arrays.asList(house1, house2);
        when(houseRepository.findAll()).thenReturn(houseList);
        HouseDto houseDto1 = new HouseDto();
        HouseDto houseDto2 = new HouseDto();
        List<HouseDto> expectedHouseDtoList = Arrays.asList(houseDto1, houseDto2);
        when(houseMapper.toDto(house1)).thenReturn(houseDto1);
        when(houseMapper.toDto(house2)).thenReturn(houseDto2);

        List<HouseDto> result = houseService.getAllHouses();

        assertEquals(expectedHouseDtoList, result);
    }

    @Test
    void getHouseById() throws JsonProcessingException {
        Long houseId = 1L;
        House house = mock(House.class);
        when(houseRepository.findById(houseId)).thenReturn(Optional.of(house));
        HouseDto houseDto = new HouseDto();
        when(houseMapper.toDto(house)).thenReturn(houseDto);

        HouseDto result = houseService.getHouseById(houseId);

        verify(houseRepository).findById(houseId);
        verify(houseMapper).toDto(house);

        assertEquals(houseDto, result);
    }

    @Test
    void overwriteHouseById() {
        Long houseId = 1L;
        HouseDto houseDto = new HouseDto();
        houseDto.setAddress("New Address");

        House existingHouse = new House();
        when(houseRepository.findById(houseId)).thenReturn(Optional.of(existingHouse));
        when(houseRepository.save(existingHouse)).thenReturn(existingHouse);

        houseService.overwriteHouseById(houseId, houseDto);

        verify(houseRepository).findById(houseId);
        verify(houseRepository).save(existingHouse);
    }

    @Test
    void deleteById() {
        Long houseId = 1L;
        when(houseRepository.existsById(houseId)).thenReturn(true);

        houseService.deleteById(houseId);

        verify(houseRepository).existsById(houseId);
        verify(houseRepository).deleteById(houseId);
    }
}