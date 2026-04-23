package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.dto.UserRequestDTO;
import com.lipari.Academy2026.dto.UserResponseDTO;
import com.lipari.Academy2026.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserRequestDTO toRequestDto(UserEntity userEntity);
    UserResponseDTO toResponseDto(UserEntity userEntity);
    
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    @Mapping(target = "roles", ignore = true)
    UserEntity toEntity(UserDTO userDTO);
    
    List<UserDTO> toDtoList(List<UserEntity> userEntityList);

    @Mapping(target = "role", expression = "java(userEntity.getRoles().stream().findFirst().map(r -> r.getName().name()).orElse(null))")
    UserDTO toDto(UserEntity userEntity);
}
