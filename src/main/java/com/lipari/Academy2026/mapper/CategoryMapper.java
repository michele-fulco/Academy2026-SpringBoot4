package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import com.lipari.Academy2026.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    @Mapping(target = "products", ignore = true)
    CategoryDTO toDto(CategoryEntity pEntity);

    CategoryEntity toEntity(CategoryDTO pDto);

    List<CategoryDTO> toDtoList(List<CategoryEntity> cEntityList);
}
