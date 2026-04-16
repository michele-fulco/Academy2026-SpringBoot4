package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {

    @Mapping(source = "imageUrl", target = "imageUrl")
    ProductDTO toDto(ProductEntity pEntity);

    List<ProductDTO> toDtoList(List<ProductEntity> pEntityList);

    @Mapping(source = "imageUrl", target = "imageUrl")
    ProductEntity toEntity(ProductDTO pDto);

}
