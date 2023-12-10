package com.bookservice.gametimebooking.mapper;

import com.bookservice.gametimebooking.dto.UserDto;
import com.bookservice.gametimebooking.model.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User entity);
}
