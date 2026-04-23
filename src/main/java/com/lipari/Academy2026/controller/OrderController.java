package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<OrderDTO> checkout(@RequestBody List<OrderRequestDTO> items, @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(orderService.processCheckout(items, user));
    }


    @GetMapping("/my-orders")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<OrderDTO>> getMyOrders(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(user.getId()));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
