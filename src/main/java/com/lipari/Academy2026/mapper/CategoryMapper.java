package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    @Mapping(target = "products", ignore = true)
    CategoryDTO toDto(CategoryEntity pEntity);

    CategoryEntity toEntity(CategoryDTO pDto);

}
