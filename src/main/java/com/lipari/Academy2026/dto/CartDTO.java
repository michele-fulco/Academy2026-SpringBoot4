package com.lipari.Academy2026.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CartDTO {
    private Long id;
    private LocalDateTime createdAt;
    private Integer quantity;
    private ProductDTO product;
}
