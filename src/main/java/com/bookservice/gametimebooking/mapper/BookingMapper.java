package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.BookingDto;
import com.bookservice.gametimebooking.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookingMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "resource.id", target = "resourceId")
    @Mapping(source = "resource.service.serviceName", target = "serviceName")
    @Mapping(source = "resource.service.house.address", target = "address")

    BookingDto toDto(Booking entity);


}
