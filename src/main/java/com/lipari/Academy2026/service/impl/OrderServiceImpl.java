package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;
import com.lipari.Academy2026.entity.CartEntity;
import com.lipari.Academy2026.entity.OrderEntity;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.mapper.OrderMapper;
import com.lipari.Academy2026.repository.OrderRepository;
import com.lipari.Academy2026.repository.ProductRepository;
import com.lipari.Academy2026.repository.UserRepository;
import com.lipari.Academy2026.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderDTO processCheckout(List<OrderRequestDTO> checkoutRequest, String userId) {
        UserEntity user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        OrderEntity order = new OrderEntity();
        order.setUser(user);

        List<CartEntity> orderItems = checkoutRequest.stream().map(request -> {
            ProductEntity product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Prodotto non trovato: " + request.getProductId()));

            CartEntity item = new CartEntity();
            item.setProduct(product);
            item.setQuantity(request.getQuantity());
            item.setOrder(order);
            item.setCreatedAt(LocalDateTime.now());
            return item;
        }).collect(Collectors.toList());

        order.setCartEntityList(orderItems);
        OrderEntity savedOrder = orderRepository.save(order);

        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(String userId) {
        return orderMapper.toDtoList(orderRepository.findByUserId(Long.parseLong(userId)));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }
}
