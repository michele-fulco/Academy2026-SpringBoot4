package com.lipari.Academy2026.repository;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
//    @Query("SELECT p FROM ProductEntity p WHERE "+
//            "LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<ProductEntity> searchProducts(String name);
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
    

}
