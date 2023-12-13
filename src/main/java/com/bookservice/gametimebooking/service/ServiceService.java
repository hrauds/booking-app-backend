package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.ServiceDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.ServiceMapper;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.repository.HouseRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceService {

    private ServiceRepository serviceRepository;
    private ServiceMapper serviceMapper;
    private HouseRepository houseRepository;
    private static final String SERVICE_NOT_FOUND_ERROR_MESSAGE = "Service not found";

    public ServiceDto createService(ServiceDto serviceDto) {
        House house = houseRepository.findById(serviceDto.getHouseId())
                .orElseThrow(() -> new UserException(SERVICE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));

        com.bookservice.gametimebooking.model.Service service = serviceMapper.toEntity(serviceDto);

        house.addService(service);

        serviceRepository.save(service);

        log.info("Service was successfully saved");
        return serviceMapper.toDto(service);
    }

    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll().stream().map(serviceMapper::toDto).toList();
    }

    public ServiceDto getServiceById(Long id) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new UserException(SERVICE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));

        log.info("Service was successfully found");
        return serviceMapper.toDto(service);
    }

    public void overwriteServiceById(Long id, ServiceDto serviceDto) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new UserException(SERVICE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));

        service.setServiceName(serviceDto.getServiceName());

        serviceRepository.save(service);
        log.info("Service was successfully updated and saved");
    }

    public void deleteById(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new UserException(SERVICE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        serviceRepository.deleteById(id);
        log.info("Service was successfully deleted");
    }

    public List<ServiceDto> getServicesByHouseId(Long houseId) {
        if (!houseRepository.existsById(houseId)) {
            throw new UserException(SERVICE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        List<com.bookservice.gametimebooking.model.Service> services = serviceRepository.findServicesByHouse_Id(houseId);
        log.info("Service was successfully found by HouseId");
        return services.stream().map(serviceMapper::toDto).toList();
    }
}
