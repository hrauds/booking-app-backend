package com.bookservice.gametimebooking.controller.resource;

import com.bookservice.gametimebooking.controller.resource.dto.CreateResourceRequest;
import com.bookservice.gametimebooking.controller.resource.dto.CreateResourceResponse;
import com.bookservice.gametimebooking.controller.resource.dto.OverwriteResourceRequest;
import com.bookservice.gametimebooking.controller.resource.dto.ResourceResponse;
import com.bookservice.gametimebooking.service.resource.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resource")
@AllArgsConstructor
public class ResourceController {

    private ResourceService resourceService;

    @PostMapping
    public CreateResourceResponse createHouse(@RequestBody CreateResourceRequest createResourceRequest){
        return resourceService.createResource(createResourceRequest);
    }

    @GetMapping
    public List<ResourceResponse> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/{id}")
    public ResourceResponse getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteResourceById(@PathVariable Long id, @RequestBody OverwriteResourceRequest request) {
        resourceService.overwriteResourceById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteResourceById(@PathVariable Long id) {
        resourceService.deleteById(id);
    }
}
