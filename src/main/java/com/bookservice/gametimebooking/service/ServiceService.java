package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.ServiceDto;
import com.bookservice.gametimebooking.exceptions.ServiceNotFoundException;
import com.bookservice.gametimebooking.mapper.ServiceMapper;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.repository.HouseRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {

    private ServiceRepository serviceRepository;
    private ServiceMapper serviceMapper;
    private HouseRepository houseRepository;
    private static final String SERVICE_NOT_FOUND_ERROR_MESSAGE = "Service not found";

    public ServiceDto createService(ServiceDto serviceDto) {
        House house = houseRepository.findById(serviceDto.getHouseId())
                .orElseThrow(() -> new ServiceNotFoundException(SERVICE_NOT_FOUND_ERROR_MESSAGE));

        com.bookservice.gametimebooking.model.Service service = serviceMapper.toEntity(serviceDto);

        house.addService(service);

        serviceRepository.save(service);

        return serviceMapper.toDto(service);
    }

    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll().stream().map(serviceMapper::toDto).toList();
    }

    public ServiceDto getServiceById(Long id) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(SERVICE_NOT_FOUND_ERROR_MESSAGE));

        return serviceMapper.toDto(service);
    }

    public void overwriteServiceById(Long id, ServiceDto serviceDto) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new ServiceNotFoundException(SERVICE_NOT_FOUND_ERROR_MESSAGE));

        service.setServiceName(serviceDto.getServiceName());

        serviceRepository.save(service);
    }

    public void deleteById(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new ServiceNotFoundException(SERVICE_NOT_FOUND_ERROR_MESSAGE);
        }
        serviceRepository.deleteById(id);
    }

    public List<ServiceDto> getServicesByHouseId(Long houseId) {
        if (!houseRepository.existsById(houseId)) {
            throw new ServiceNotFoundException(SERVICE_NOT_FOUND_ERROR_MESSAGE);
        }
        List<com.bookservice.gametimebooking.model.Service> services = serviceRepository.findServicesByHouse_Id(houseId);
        return services.stream().map(serviceMapper::toDto).toList();
    }
}
