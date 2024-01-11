package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.ServiceDto;
import com.bookservice.gametimebooking.service.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Service", description = "Services endpoints")
@AllArgsConstructor
@Slf4j
public class ServiceController {

    private ServiceService serviceService;

    private static final String ENDPOINT_NAME = "/service";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @Operation(summary = "Create a Service")
    @ApiResponse(responseCode = "201", description = "Service was successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(ENDPOINT_NAME)
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceDto createService(@RequestBody ServiceDto serviceDto){
        log.info("Request to create a Service");
        return serviceService.createService(serviceDto);
    }

    @Operation(summary = "Get all Services")
    @ApiResponse(responseCode = "200", description = "Services were found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<ServiceDto> getAllServices() {
        log.info("Request to get all Services");
        return serviceService.getAllServices();
    }

    @Operation(summary = "Get specific Service by its id")
    @ApiResponse(responseCode = "200", description = "Service was found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public ServiceDto getServiceById(@PathVariable Long id) {
        log.info("Request to get a Service by id");
        return serviceService.getServiceById(id);
    }

    @Operation(summary = "Overwrite service by it id")
    @ApiResponse(responseCode = "204", description = "Service was changed")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteServiceById(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        log.info("Request to update a Service by id");
        serviceService.overwriteServiceById(id, serviceDto);
    }

    @Operation(summary = "Delete Service by its id")
    @ApiResponse(responseCode = "204", description = "Service was deleted")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceById(@PathVariable Long id) {
        log.info("Request to delete a Service by id");
        serviceService.deleteById(id);
    }

    @Operation(summary = "Get specific Service by its id")
    @ApiResponse(responseCode = "200", description = "Service was found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/byHouseId/{houseId}")
    public List<ServiceDto> getServicesByHouseId(@PathVariable Long houseId) {
        log.info("Request to get a Service by HouseId");
        return serviceService.getServicesByHouseId(houseId);
    }
}
