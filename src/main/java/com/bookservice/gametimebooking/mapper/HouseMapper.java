package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.HouseDto;
import com.bookservice.gametimebooking.model.House;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HouseMapper {

    @Mapping(source = "company.id", target = "companyId")
    HouseDto toDto(House entity);

    House toEntity(HouseDto dto);
}
