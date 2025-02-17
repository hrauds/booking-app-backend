package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.BookableDto;
import com.bookservice.gametimebooking.model.Bookable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookableMapper {

    @Mapping(source = "resource.id", target = "resourceId")
    @Mapping(source = "resource.resourceName", target = "resourceName")
    @Mapping(source = "resource.service.serviceName", target = "serviceName")

    BookableDto toDto(Bookable entity);
}
