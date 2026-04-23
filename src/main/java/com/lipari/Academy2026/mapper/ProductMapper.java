package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDTO toDto(ProductEntity pEntity);

    List<ProductDTO> toDtoList(List<ProductEntity> pEntityList);

    @Mapping(target = "cartEntityList", ignore = true)
    ProductEntity toEntity(ProductDTO pDto);

}
