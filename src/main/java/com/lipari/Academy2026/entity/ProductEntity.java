package com.lipari.Academy2026.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    BigDecimal oldPrice;
    BigDecimal price;
    String brand;
    String image;
    Boolean isNew;
    /*@ManyToOne
    @JoinColumn(name = "user_id") //rinumona : seller id
    private UserEntity seller;*/
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
