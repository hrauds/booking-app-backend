package com.bookservice.gametimebooking.service.resource;

import com.bookservice.gametimebooking.controller.resource.dto.CreateResourceRequest;
import com.bookservice.gametimebooking.controller.resource.dto.CreateResourceResponse;
import com.bookservice.gametimebooking.controller.resource.dto.OverwriteResourceRequest;
import com.bookservice.gametimebooking.controller.resource.dto.ResourceResponse;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResourceService {

    private ResourceRepository resourceRepository;
    private ServiceRepository serviceRepository;

    private ResourceMapper resourceMapper;

    public CreateResourceResponse createResource(CreateResourceRequest createResourceRequest) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(createResourceRequest.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Resource resource = resourceMapper.toEntity(createResourceRequest);

        service.addResource(resource);

        return resourceMapper.toCreateResourceResponse(resource);
    }

    public List<ResourceResponse> getAllResources() {
        return resourceRepository.findAll().stream().map(resourceMapper::toResourceResponse).collect(Collectors.toList());
    }

    public ResourceResponse getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        return resourceMapper.toResourceResponse(resource);
    }

    public void overwriteResourceById(Long id, OverwriteResourceRequest request) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        resource.setResourceName(resource.getResourceName());

        resourceRepository.save(resource);
    }

    public void deleteById(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new RuntimeException("Resource not found");
        }
        resourceRepository.deleteById(id);
    }
}
