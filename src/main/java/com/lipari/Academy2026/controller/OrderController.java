package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;
import com.lipari.Academy2026.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    //dummy
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<OrderDTO> checkout(@RequestBody List<OrderRequestDTO> items, @PathVariable String userId) {
        // In una versione reale con Spring Security, l'ID utente verrebbe dal SecurityContext
        return ResponseEntity.ok(orderService.processCheckout(items, userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getMyOrders(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
