package com.lipari.Academy2026.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDateTime createdAt;
    Integer quantity;
    @ManyToOne
    @JoinColumn(name="product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name="order_id")
    private OrderEntity order;
    //String productId;  //join tabella product?
}
