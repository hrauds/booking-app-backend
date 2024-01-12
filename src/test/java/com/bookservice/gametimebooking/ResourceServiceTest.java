package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.ResourceDto;
import com.bookservice.gametimebooking.mapper.ResourceMapper;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.model.Service;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import com.bookservice.gametimebooking.repository.ServiceRepository;
import com.bookservice.gametimebooking.service.ResourceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResourceServiceTest {

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ResourceMapper resourceMapper;

    @InjectMocks
    private ResourceService resourceService;

    @Test
    void createResource() {
        Long serviceId = 1L;
        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setServiceId(serviceId);

        Service service = new Service();
        when(serviceRepository.findById(serviceId)).thenReturn(Optional.of(service));

        Resource expectedResource = new Resource();
        when(resourceMapper.toEntity(resourceDto)).thenReturn(expectedResource);
        when(resourceRepository.save(expectedResource)).thenReturn(expectedResource);

        ResourceDto result = resourceService.createResource(resourceDto);

        verify(serviceRepository).findById(serviceId);
        verify(resourceMapper).toEntity(resourceDto);
        verify(resourceRepository).save(expectedResource);

        assertEquals(resourceMapper.toDto(expectedResource), result);
    }

    @Test
    void getAllResources() {

        Resource resource1 = new Resource();
        Resource resource2 = new Resource();
        List<Resource> resourceList = Arrays.asList(resource1, resource2);

        when(resourceRepository.findAll()).thenReturn(resourceList);

        ResourceDto resourceDto1 = new ResourceDto();
        ResourceDto resourceDto2 = new ResourceDto();
        List<ResourceDto> expectedResourceDtoList = Arrays.asList(resourceDto1, resourceDto2);

        when(resourceMapper.toDto(resource1)).thenReturn(resourceDto1);
        when(resourceMapper.toDto(resource2)).thenReturn(resourceDto2);

        List<ResourceDto> result = resourceService.getAllResources();

        verify(resourceRepository).findAll();
        verify(resourceMapper, times(2)).toDto(any(Resource.class));
        assertEquals(expectedResourceDtoList, result);
    }

    @Test
    void getResourceById() {
        Long resourceId = 1L;
        Resource resource = new Resource();
        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));

        ResourceDto expectedResourceDto = new ResourceDto();
        when(resourceMapper.toDto(resource)).thenReturn(expectedResourceDto);

        ResourceDto result = resourceService.getResourceById(resourceId);

        verify(resourceRepository).findById(resourceId);
        verify(resourceMapper).toDto(resource);

        assertEquals(expectedResourceDto, result);
    }

    @Test
    void overwriteResourceById() {
        Long resourceId = 1L;

        String newResourceName = "resource 1";
        ResourceDto resourceDto = new ResourceDto();
        resourceDto.setResourceName(newResourceName);

        Resource existingResource = new Resource();

        when(resourceRepository.findById(1L)).thenReturn(Optional.of(existingResource));
        when(resourceRepository.save(existingResource)).thenReturn(existingResource);

        resourceService.overwriteResourceById(resourceId, resourceDto);

        verify(resourceRepository).findById(1L);
        verify(resourceRepository).save(existingResource);
    }

}
