package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.CartDTO;
import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;
import com.lipari.Academy2026.entity.CartEntity;
import com.lipari.Academy2026.entity.OrderEntity;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.exceptions.BadRequestException;
import com.lipari.Academy2026.mapper.OrderMapper;
import com.lipari.Academy2026.repository.OrderRepository;
import com.lipari.Academy2026.repository.ProductRepository;
import com.lipari.Academy2026.repository.UserRepository;
import com.lipari.Academy2026.service.OrderService;
import lombok.RequiredArgsConstructor;
import com.lipari.Academy2026.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public OrderDTO processCheckout(List<OrderRequestDTO> checkoutRequest, UserEntity user) {
        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setUsername(user.getUsername());

        // Inizializziamo il totale a zero
        final java.math.BigDecimal[] totalOrder = {java.math.BigDecimal.ZERO};

        List<CartEntity> orderItems = checkoutRequest.stream().map(request -> {
            ProductEntity product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Prodotto non trovato con id: " + request.getProductId()));

            // Calcoliamo il prezzo per questo item e lo aggiungiamo al totale
            java.math.BigDecimal itemTotal = product.getPrice().multiply(java.math.BigDecimal.valueOf(request.getQuantity()));
            totalOrder[0] = totalOrder[0].add(itemTotal);

            CartEntity item = new CartEntity();
            item.setProduct(product);
            item.setQuantity(request.getQuantity());
            item.setOrder(order);
            item.setCreatedAt(LocalDateTime.now());
            return item;
        }).collect(Collectors.toList());

        order.setCartEntityList(orderItems);
        order.setTotal(totalOrder[0]); // <--- SETTIAMO IL TOTALE CALCOLATO

        OrderEntity savedOrder = orderRepository.save(order);

        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        return orderMapper.toDtoList(orderRepository.findByUserId(userId));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }
    @Transactional
    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = this.orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ordine da modificare con id " + orderDTO.getId() + " non trovato"));
        orderEntity.getCartEntityList().clear();
        final java.math.BigDecimal[] totalOrder = {java.math.BigDecimal.ZERO};
        if (orderDTO.getItems() != null){
            for (CartDTO itemDto : orderDTO.getItems()){
                ProductEntity product = productRepository.findById(itemDto.getProduct().getId())
                        .orElseThrow(()->new ResourceNotFoundException("Prodotto da inserire con id" + itemDto.getProduct().getId() +" non trovato"));
                if (itemDto.getQuantity() == null || itemDto.getQuantity() <= 0) {
                    throw new BadRequestException("La quantità per il prodotto " + product.getTitle() + " deve essere maggiore di zero");
                }
                CartEntity newCart = new CartEntity();
                newCart.setProduct(product);
                newCart.setQuantity(itemDto.getQuantity());
                newCart.setCreatedAt(orderEntity.getCreationDate());
                newCart.setOrder(orderEntity);
                BigDecimal itemTotal = product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity()));
                totalOrder[0]=totalOrder[0].add(itemTotal);
                orderEntity.getCartEntityList().add(newCart);
            }
        }
        orderEntity.setUser(orderEntity.getUser());
        orderEntity.setTotal(totalOrder[0]);
        OrderEntity updatedOrder = this.orderRepository.save(orderEntity);
        return orderMapper.toDto(updatedOrder);
    }
    @Transactional
    @Override
    public void softDelete(Long id) {
        OrderEntity orderEntity = this.orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ordine da disabilitare con id " + id + " non trovato"));

        // Usiamo Boolean.TRUE.equals per gestire in sicurezza il valore null
        // Se è null o false, diventa true. Se è true, diventa false.
        boolean isCurrentlyDeactivated = Boolean.TRUE.equals(orderEntity.getDeactivated());
        orderEntity.setDeactivated(!isCurrentlyDeactivated);

        // Usiamo saveAndFlush per forzare l'aggiornamento immediato nel DB
        this.orderRepository.saveAndFlush(orderEntity);
    }
}
