package com.lipari.Academy2026.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    @Column(name="des")
    String description;
    BigDecimal oldPrice; //numeric 2 cif dec.
    BigDecimal price;
    String brand;
    @Column(name="image")
    String imageUrl;
    Boolean isNew;
    /*@ManyToOne
    @JoinColumn(name = "user_id") //rinumona : seller id
    private UserEntity seller;*/
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
