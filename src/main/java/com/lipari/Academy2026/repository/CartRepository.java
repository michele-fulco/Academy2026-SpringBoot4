package com.lipari.Academy2026.repository;

import com.lipari.Academy2026.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
}
