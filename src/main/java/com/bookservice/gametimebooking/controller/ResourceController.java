package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.ResourceDto;
import com.bookservice.gametimebooking.service.ResourceService;
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
@Tag(name = "Resource", description = "Resource endpoints")
@AllArgsConstructor
@Slf4j
public class ResourceController {

    private ResourceService resourceService;

    private static final String ENDPOINT_NAME = "/resource";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @Operation(summary = "Create a new Resource")
    @ApiResponse(responseCode = "201", description = "Resource was successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(ENDPOINT_NAME)
    @ResponseStatus(HttpStatus.CREATED)
    public ResourceDto createResource(@RequestBody ResourceDto resourceDto){
        log.info("Request to create a Resource");
        return resourceService.createResource(resourceDto);
    }

    @Operation(summary = "Get all Resources")
    @ApiResponse(responseCode = "200", description = "Resources were found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<ResourceDto> getAllResources() {
        log.info("Request to get all Resources");
        return resourceService.getAllResources();
    }

    @Operation(summary = "Get Resource by its id")
    @ApiResponse(responseCode = "200", description = "Resource was found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public ResourceDto getResourceById(@PathVariable Long id) {
        log.info("Request to get a Resource by id");
        return resourceService.getResourceById(id);
    }

    @Operation(summary = "Overwrite Resource")
    @ApiResponse(responseCode = "204", description = "Resource was changed")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteResourceById(@PathVariable Long id, @RequestBody ResourceDto resourceDto) {
        log.info("Request to update a Resource by id");
        resourceService.overwriteResourceById(id, resourceDto);
    }

    @Operation(summary = "Delete Resource by its id")
    @ApiResponse(responseCode = "204", description = "Resource was deleted")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResourceById(@PathVariable Long id) {
        log.info("Request to delete a Resource by id");
        resourceService.deleteById(id);
    }
}
