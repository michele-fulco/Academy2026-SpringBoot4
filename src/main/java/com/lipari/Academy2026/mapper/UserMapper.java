package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.dto.UserDTO;
import com.lipari.Academy2026.dto.UserRequestDTO;
import com.lipari.Academy2026.dto.UserResponseDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestDTO toRequestDto(UserEntity userEntity);
    UserResponseDTO toResponseDto(UserEntity userEntity);
    UserEntity toEntity(UserDTO userDTO);
    List<UserDTO> toDtoList(List<UserEntity> userEntityList);

    @Mapping(target = "role", expression = "java(userEntity.getRoles().stream().findFirst().map(r -> r.getName().name()).orElse(null))")
    UserDTO toDto(UserEntity userEntity);
}
