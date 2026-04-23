package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.CartDTO;
import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.entity.CartEntity;
import com.lipari.Academy2026.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(source = "cartEntityList", target = "items")
    @Mapping(source = "deactivated", target = "deactivated") // <-- AGGIUNGI QUESTA RIGA
    OrderDTO toDto(OrderEntity orderEntity);

    List<OrderDTO> toDtoList(List<OrderEntity> orderEntityList);

    @Mapping(source = "items", target = "cartEntityList")
    @Mapping(source = "deactivated", target = "deactivated") // <-- AGGIUNGI ANCHE QUI per l'update
    @Mapping(target = "user", ignore = true)
    OrderEntity toEntity(OrderDTO orderDTO);

    CartDTO cartToCartDto(CartEntity cartEntity);

    @Mapping(target = "order", ignore = true)
    CartEntity cartDtoToCartEntity(CartDTO cartDTO);
}
