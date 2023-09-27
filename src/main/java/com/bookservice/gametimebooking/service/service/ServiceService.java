package com.bookservice.gametimebooking.service.service;

import com.bookservice.gametimebooking.controller.service.dto.CreateServiceRequest;
import com.bookservice.gametimebooking.controller.service.dto.CreateServiceResponse;
import com.bookservice.gametimebooking.controller.service.dto.OverwriteServiceRequest;
import com.bookservice.gametimebooking.controller.service.dto.ServiceResponse;
import com.bookservice.gametimebooking.model.House;
import com.bookservice.gametimebooking.repository.HouseRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceService {

    private ServiceRepository serviceRepository;
    private ServiceMapper serviceMapper;

    private HouseRepository houseRepository;

    public CreateServiceResponse createService(CreateServiceRequest createServiceRequest) {
        House house = houseRepository.findById(createServiceRequest.getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found"));

        com.bookservice.gametimebooking.model.Service service = serviceMapper.toEntity(createServiceRequest);

        house.addService(service);

        serviceRepository.save(service);

        return serviceMapper.toCreateServiceResponse(service);
    }

    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll().stream().map(serviceMapper::toServiceResponse).collect(Collectors.toList());
    }

    public ServiceResponse getServiceById(Long id) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        return serviceMapper.toServiceResponse(service);
    }

    public void overwriteServiceById(Long id, OverwriteServiceRequest request) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setServiceName(request.getServiceName());

        serviceRepository.save(service);
    }

    public void deleteById(Long id) {
        if (!serviceRepository.existsById(id)) {
            throw new RuntimeException("Service not found");
        }
        serviceRepository.deleteById(id);
    }
}
