package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;
import com.lipari.Academy2026.entity.UserEntity;

import java.util.List;

public interface OrderService {
    OrderDTO processCheckout(List<OrderRequestDTO> checkoutRequest, UserEntity user);
    List<OrderDTO> getOrdersByUserId(Long userId);
    List<OrderDTO> getAllOrders();
}
