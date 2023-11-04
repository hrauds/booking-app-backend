package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.ServiceDto;
import com.bookservice.gametimebooking.model.Service;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {

    @Mapping(source = "house.id", target = "houseId")
    ServiceDto toDto(Service entity);

    Service toEntity(ServiceDto dto);
}
