package com.lipari.Academy2026.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal total;

    @Column(nullable = false)
    Boolean deactivated = false;

    String username;


    private java.time.LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartEntity> cartEntityList;

    @PrePersist
    protected void onCreate() {
        creationDate = java.time.LocalDateTime.now();
    }
}
