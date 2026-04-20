package com.lipari.Academy2026.mapper;

import com.lipari.Academy2026.dto.CartDTO;
import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.entity.CartEntity;
import com.lipari.Academy2026.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderMapper {

    @Mapping(source = "cartEntityList", target = "items")
    OrderDTO toDto(OrderEntity orderEntity);

    List<OrderDTO> toDtoList(List<OrderEntity> orderEntityList);

    @Mapping(source = "items", target = "cartEntityList")
    OrderEntity toEntity(OrderDTO orderDTO);

    CartDTO cartToCartDto(CartEntity cartEntity);
    CartEntity cartDtoToCartEntity(CartDTO cartDTO);
}
