package com.lipari.Academy2026.repository;

import com.lipari.Academy2026.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByNameIgnoreCase(String name);
}
