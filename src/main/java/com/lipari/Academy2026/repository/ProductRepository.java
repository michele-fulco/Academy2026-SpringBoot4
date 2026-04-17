package com.lipari.Academy2026.repository;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
//    @Query("SELECT p FROM ProductEntity p WHERE "+
//            "LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<ProductEntity> searchProducts(String name);
    List<ProductEntity> findByTitleContainingIgnoreCase(String name);
    /*@Query("SELECT p FROM ProductEntity p WHERE p.id = :id")
    ProductEntity findByIdLong(@Param("id") Long id);*/
    //Optional<ProductEntity> findById(Long id);
    Optional<ProductEntity> findByTitleIgnoreCase(String title);
    //void deleteById(Long id);

}
