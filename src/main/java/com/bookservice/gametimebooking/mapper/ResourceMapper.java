package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.ResourceDto;
import com.bookservice.gametimebooking.model.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {

    @Mapping(source = "service.id", target = "serviceId")
    ResourceDto toDto(Resource entity);

    Resource toEntity(ResourceDto dto);
}
