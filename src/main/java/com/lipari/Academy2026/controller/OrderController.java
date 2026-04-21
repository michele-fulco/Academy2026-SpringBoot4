package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<OrderDTO> checkout(@RequestBody List<OrderRequestDTO> items, Authentication authentication) {
        UserEntity userDetails = (UserEntity) authentication.getPrincipal();
        // Poiché OrderServiceImpl aspetta una String userId, convertiamo l'ID se necessario 
        // o adattiamo il service. UserEntity ha un Long id.
        return ResponseEntity.ok(orderService.processCheckout(items, String.valueOf(userDetails.getId())));
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<OrderDTO>> getMyOrders(Authentication authentication) {
        UserEntity userDetails = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(orderService.getOrdersByUserId(String.valueOf(userDetails.getId())));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
