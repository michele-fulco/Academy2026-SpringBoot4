package com.lipari.Academy2026.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotEmpty
    String title;
    @Column(name="description")
    String description;
    BigDecimal oldPrice; //numeric 2 cif dec.
    BigDecimal price;
    String brand;
    @Column(name="image_url")
    String imageUrl;
    Boolean isNew;
    Boolean deactivated;
    /*@ManyToOne
    @JoinColumn(name = "user_id") //rinumona : seller id
    private UserEntity seller;*/
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @OneToMany(mappedBy = "product")
    private List<CartEntity> cartEntityList;
}
