package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.ResourceDto;
import com.bookservice.gametimebooking.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ResourceController {

    private ResourceService resourceService;

    private static final String ENDPOINT_NAME = "/resource";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(ENDPOINT_NAME)
    public ResourceDto createResource(@RequestBody ResourceDto resourceDto){
        return resourceService.createResource(resourceDto);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<ResourceDto> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public ResourceDto getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id);
    }

    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteResourceById(@PathVariable Long id, @RequestBody ResourceDto resourceDto) {
        resourceService.overwriteResourceById(id, resourceDto);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    public void deleteResourceById(@PathVariable Long id) {
        resourceService.deleteById(id);
    }
}
