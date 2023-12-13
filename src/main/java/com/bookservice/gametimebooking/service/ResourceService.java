package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.ResourceDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.ResourceMapper;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class ResourceService {

    private ResourceRepository resourceRepository;
    private ServiceRepository serviceRepository;
    private ResourceMapper resourceMapper;
    private static final String RESOURCE_NOT_FOUND_ERROR_MESSAGE = "Resource not found";

    public ResourceDto createResource(ResourceDto resourceDto) {
        com.bookservice.gametimebooking.model.Service service = serviceRepository.findById(resourceDto.getServiceId())
                .orElseThrow(() -> new UserException(RESOURCE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));

        Resource resource = resourceMapper.toEntity(resourceDto);

        service.addResource(resource);

        log.info("Resource was successfully saved");
        return resourceMapper.toDto(resourceRepository.save(resource));
    }

    public List<ResourceDto> getAllResources() {
        return resourceRepository.findAll().stream()
                .map(resourceMapper::toDto)
                .toList();
    }

    public ResourceDto getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new UserException(RESOURCE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));
        log.info("Resource was successfully found");
        return resourceMapper.toDto(resource);
    }

    public void overwriteResourceById(Long id, ResourceDto resourceDto) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new UserException(RESOURCE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));

        resource.setResourceName(resourceDto.getResourceName());

        resourceRepository.save(resource);
        log.info("Resource was successfully updated and saved");
    }

    public void deleteById(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new UserException(RESOURCE_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        resourceRepository.deleteById(id);
        log.info("Resource was successfully deleted");
    }
}
