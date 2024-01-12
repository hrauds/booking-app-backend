package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.ServiceDto;
import com.bookservice.gametimebooking.mapper.ServiceMapper;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.model.Service;
import com.bookservice.gametimebooking.repository.HouseRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import com.bookservice.gametimebooking.service.ServiceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceServiceTest {

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceMapper serviceMapper;

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private Logger log;

    @InjectMocks
    private ServiceService serviceService;

    @Test
    void createService() {
        Long houseId = 1L;
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setHouseId(houseId);

        House house = new House();
        when(houseRepository.findById(houseId)).thenReturn(Optional.of(house));

        Service expectedService = new Service();
        when(serviceMapper.toEntity(serviceDto)).thenReturn(expectedService);
        when(serviceRepository.save(expectedService)).thenReturn(expectedService);

        ServiceDto result = serviceService.createService(serviceDto);

        verify(houseRepository).findById(houseId);
        verify(serviceMapper).toEntity(serviceDto);
        verify(serviceRepository).save(expectedService);

        assertEquals(serviceMapper.toDto(expectedService), result);
    }

    @Test
    void getAllServices() {
        Service service1 = new Service();
        Service service2 = new Service();
        List<Service> serviceList = Arrays.asList(service1, service2);
        when(serviceRepository.findAll()).thenReturn(serviceList);

        ServiceDto serviceDto1 = new ServiceDto();
        ServiceDto serviceDto2 = new ServiceDto();
        List<ServiceDto> expectedServiceDtoList = Arrays.asList(serviceDto1, serviceDto2);

        when(serviceMapper.toDto(service1)).thenReturn(serviceDto1);
        when(serviceMapper.toDto(service2)).thenReturn(serviceDto2);

        List<ServiceDto> result = serviceService.getAllServices();

        verify(serviceRepository).findAll();
        verify(serviceMapper, times(2)).toDto(any(com.bookservice.gametimebooking.model.Service.class));

        assertEquals(expectedServiceDtoList, result);
    }

    @Test
    void getServiceById() {
        Long serviceId = 1L;
        Service service = new Service();
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));
        when(serviceMapper.toDto(service)).thenReturn(new ServiceDto());

        ServiceDto result = serviceService.getServiceById(serviceId);

        verify(serviceRepository).findById(serviceId);
        verify(serviceMapper).toDto(service);

        assertEquals(new ServiceDto(), result);
    }

    @Test
    void overwriteServiceById() {
        Long serviceId = 1L;
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setServiceName("New Service Name");

        Service service = new Service();
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));
        when(serviceRepository.save(service)).thenReturn(service);

        serviceService.overwriteServiceById(serviceId, serviceDto);

        verify(serviceRepository).findById(serviceId);
        verify(serviceRepository).save(service);
    }

    @Test
    void deleteById() {
        Long serviceId = 1L;
        when(serviceRepository.existsById(serviceId)).thenReturn(true);

        serviceService.deleteById(serviceId);

        verify(serviceRepository).existsById(serviceId);
        verify(serviceRepository).deleteById(serviceId);
    }

    @Test
    void getServicesByHouseId() {
        Long houseId = 1L;
        House house = new House();
        when(houseRepository.existsById(houseId)).thenReturn(true);

        Service service1 = new Service();
        Service service2 = new Service();
        List<Service> services = Arrays.asList(service1, service2);

        when(serviceRepository.findServicesByHouse_Id(houseId)).thenReturn(services);
        when(serviceMapper.toDto(service1)).thenReturn(new ServiceDto());
        when(serviceMapper.toDto(service2)).thenReturn(new ServiceDto());

        List<ServiceDto> result = serviceService.getServicesByHouseId(houseId);

        verify(houseRepository).existsById(houseId);
        verify(serviceRepository).findServicesByHouse_Id(houseId);
        verify(serviceMapper, times(2)).toDto(any(Service.class));
        assertEquals(Arrays.asList(new ServiceDto(), new ServiceDto()), result);

    }
}
