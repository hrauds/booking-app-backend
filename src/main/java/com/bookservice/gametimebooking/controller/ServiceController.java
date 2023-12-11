package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.ServiceDto;
import com.bookservice.gametimebooking.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ServiceController {

    private ServiceService serviceService;

    private static final String ENDPOINT_NAME = "/service";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(ENDPOINT_NAME)
    public ServiceDto createService(@RequestBody ServiceDto serviceDto){
        return serviceService.createService(serviceDto);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public ServiceDto getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteServiceById(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        serviceService.overwriteServiceById(id, serviceDto);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        serviceService.deleteById(id);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/byHouseId/{houseId}")
    public List<ServiceDto> getServicesByHouseId(@PathVariable Long houseId) {
        return serviceService.getServicesByHouseId(houseId);
    }
}
