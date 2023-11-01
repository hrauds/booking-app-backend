package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.model.Customer;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerDto toDto(Customer entity);

    Customer toEntity(CustomerDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Customer entity, CustomerDto dto);
}
