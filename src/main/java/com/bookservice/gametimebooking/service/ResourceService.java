package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.ResourceDto;
import com.bookservice.gametimebooking.exceptions.ResourceNotFoundException;
import com.bookservice.gametimebooking.mapper.ResourceMapper;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ResourceService {

    private ResourceRepository resourceRepository;
    private ServiceRepository serviceRepository;
    private ResourceMapper resourceMapper;
    private static final String RESOURCE_NOT_FOUND_ERROR_MESSAGE = "Resource not found";

    public ResourceDto createResource(ResourceDto resourceDto) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(resourceDto.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_ERROR_MESSAGE));

        Resource resource = resourceMapper.toEntity(resourceDto);

        service.addResource(resource);

        return resourceMapper.toDto(resource);
    }

    public List<ResourceDto> getAllResources() {
        return resourceRepository.findAll().stream()
                .map(resourceMapper::toDto)
                .toList();
    }

    public ResourceDto getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_ERROR_MESSAGE));
        return resourceMapper.toDto(resource);
    }

    public void overwriteResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_ERROR_MESSAGE));

        resource.setResourceName(resource.getResourceName());

        resourceRepository.save(resource);
    }

    public void deleteById(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND_ERROR_MESSAGE);
        }
        resourceRepository.deleteById(id);
    }
}
