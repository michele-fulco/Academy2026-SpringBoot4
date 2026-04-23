package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.OrderDTO;
import com.lipari.Academy2026.dto.OrderRequestDTO;
import com.lipari.Academy2026.entity.UserEntity;
import com.lipari.Academy2026.exceptions.BadRequestException;
import com.lipari.Academy2026.service.OrderService;
import jakarta.validation.Valid;
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

    @PutMapping("/update")
    @PreAuthorize( "hasRole('ADMIN')")
    public ResponseEntity<OrderDTO> updateOrder (@Valid @RequestBody OrderDTO orderDTO){
        if (orderDTO.getId() == null || orderDTO.getId() <= 0 || orderDTO.getItems().isEmpty()) {
            throw new BadRequestException("Dati ordine non validi per l'aggiornamento");
        }
        return ResponseEntity.ok(this.orderService.updateOrder(orderDTO));
    }
    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> softDelete(@PathVariable Long id){
        this.orderService.softDelete(id);
        return ResponseEntity.ok().build();
    }
}
