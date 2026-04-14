package com.lipari.Academy2026.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    Long id;
    String title;
    String description;
    BigDecimal oldPrice;
    BigDecimal price;
    String brand;
    String image;
    Boolean isNew;
    CategoryDTO category;
}
