package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryDTO toDto(CategoryEntity pEntity);

    @Mapping(target = "products", ignore = true)
    CategoryEntity toEntity(CategoryDTO pDto);

    List<CategoryDTO> toDtoList(List<CategoryEntity> cEntityList);
}
