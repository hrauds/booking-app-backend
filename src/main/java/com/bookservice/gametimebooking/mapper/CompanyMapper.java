package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.model.Company;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyDto toDto(Company entity);

    Company toEntity(CompanyDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Company entity, CompanyDto dto);
}
