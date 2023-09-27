package com.bookservice.gametimebooking.controller.service;

import com.bookservice.gametimebooking.controller.service.dto.CreateServiceRequest;
import com.bookservice.gametimebooking.controller.service.dto.CreateServiceResponse;
import com.bookservice.gametimebooking.controller.service.dto.OverwriteServiceRequest;
import com.bookservice.gametimebooking.controller.service.dto.ServiceResponse;
import com.bookservice.gametimebooking.service.service.ServiceService;
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
    public CreateServiceResponse createService(@RequestBody CreateServiceRequest createServiceRequest){
        return serviceService.createService(createServiceRequest);
    }

    @GetMapping
    public List<ServiceResponse> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ServiceResponse getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteServiceById(@PathVariable Long id, @RequestBody OverwriteServiceRequest request) {
        serviceService.overwriteServiceById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteServiceById(@PathVariable Long id) {
        serviceService.deleteById(id);
    }
}
