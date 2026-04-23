package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.exceptions.ResourceNotFoundException;
import com.lipari.Academy2026.mapper.ProductMapper;
import com.lipari.Academy2026.repository.CategoryRepository;
import com.lipari.Academy2026.repository.ProductRepository;
import com.lipari.Academy2026.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductMapper productMapper;

    public ProductDTO newProduct(ProductDTO productDTO) {
        ProductEntity p = this.productMapper.toEntity(productDTO);
        p.setCategory(resolveCategory(productDTO.getCategory()));
        p = this.productRepository.save(p);
        return this.productMapper.toDto(p);
    }

    public ProductDTO getProduct(Long id) {
        Optional<ProductEntity> op = this.productRepository.findById(id);
        if(op.isPresent()) {
            return this.productMapper.toDto(op.get());
        } else {
            throw new ResourceNotFoundException("Prodotto con id " + id + " non trovato");
        }
    }

    public List<ProductDTO> getProducts() {
        List<ProductEntity> list = this.productRepository.findAll();
        return this.productMapper.toDtoList(list);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Optional<ProductEntity> op = this.productRepository.findById(productDTO.getId());
        if (op.isPresent()){
            ProductEntity p = this.productMapper.toEntity(productDTO);
            p.setCategory(resolveCategory(productDTO.getCategory()));
            ProductEntity temp = productRepository.save(p);
            return this.productMapper.toDto(temp);
        } else {
            throw new ResourceNotFoundException("Prodotto con id " + productDTO.getId() + " non in catalogo");
        }
    }

    @Override
    @Transactional
    public void removeProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prodotto da eliminare con id " + id + " non trovato");
        }
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void softDeletion(Long id) {
        ProductEntity p = this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prodotto da disattivare con id " + id + " non trovato"));

        boolean isCurrentlyDeactivated = (p.getDeactivated() != null) && p.getDeactivated();
        p.setDeactivated(!isCurrentlyDeactivated);
        productRepository.save(p);
    }

    @Override
    public List<ProductDTO> getProductsByTitle(String title) {
        return this.productMapper.toDtoList(this.productRepository.findByTitleContainingIgnoreCase(title));
    }

    // Utils
    private CategoryEntity resolveCategory(CategoryDTO categoryDTO) {
        CategoryEntity category = null;
        if (categoryDTO != null && categoryDTO.getName() != null) {
            category = categoryRepository.findByNameIgnoreCase(categoryDTO.getName());
        }

        if (category == null) {
            category = categoryRepository.findByNameIgnoreCase("Misc.");
            if (category == null) {
                category = new CategoryEntity();
                category.setName("Misc.");
                category = categoryRepository.save(category);
            }
        }
        return category;
    }

    public List<ProductDTO> getActiveProducts() {
        List<ProductEntity> list = this.productRepository.findByDeactivatedFalseOrDeactivatedIsNull();
        return this.productMapper.toDtoList(list);
    }
}
