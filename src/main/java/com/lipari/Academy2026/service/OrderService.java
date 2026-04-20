package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;

import java.util.List;

public interface OrderService {
    OrderDTO processCheckout(List<OrderRequestDTO> checkoutRequest, String userId);
    List<OrderDTO> getOrdersByUserId(String userId);
    List<OrderDTO> getAllOrders();
}
