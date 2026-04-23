package com.lipari.Academy2026.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private String username;
    private LocalDateTime creationDate;
    private List<CartDTO> items;
    private Boolean deactivated;
    private BigDecimal total;

}
