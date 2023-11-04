package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.ResourceDto;
import com.bookservice.gametimebooking.mapper.ResourceMapper;
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

    public ResourceDto createResource(ResourceDto resourceDto) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(resourceDto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Resource resource = resourceMapper.toEntity(resourceDto);

        service.addResource(resource);

        return resourceMapper.toDto(resource);
    }

    public List<ResourceDto> getAllResources() {
        return resourceRepository.findAll().stream().map(resourceMapper::toDto).collect(Collectors.toList());
    }

    public ResourceDto getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        return resourceMapper.toDto(resource);
    }

    public void overwriteResourceById(Long id, ResourceDto request) {
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
