package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.ServiceDto;
import com.bookservice.gametimebooking.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
@AllArgsConstructor
public class ServiceController {

    private ServiceService serviceService;

    @PostMapping
    public ServiceDto createService(@RequestBody ServiceDto serviceDto){
        return serviceService.createService(serviceDto);
    }

    @GetMapping
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceDto getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteServiceById(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        serviceService.overwriteServiceById(id, serviceDto);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        serviceService.deleteById(id);
    }

    @GetMapping("/byHouseId/{houseId}")
    public List<ServiceDto> getServicesByHouseId(@PathVariable Long houseId) {
        return serviceService.getServicesByHouseId(houseId);
    }
}
