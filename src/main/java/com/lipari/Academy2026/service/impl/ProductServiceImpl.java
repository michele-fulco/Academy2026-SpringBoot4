package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.mapper.ProductMapper;
import com.lipari.Academy2026.repository.ProductRepository;
import com.lipari.Academy2026.service.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public ProductDTO newProduct(ProductDTO productDTO) {
        System.out.println("DTO ricevuto: " + productDTO);
        System.out.println("imageUrl nel DTO: " + productDTO.getImageUrl());

        ProductEntity p = this.productMapper.toEntity(productDTO);

        System.out.println("Entity prima del save: " + p);
        System.out.println("imageUrl nell'entity: " + p.getImageUrl());

        p = this.productRepository.save(p);

        System.out.println("Entity dopo il save: " + p);

        return this.productMapper.toDto(p);
    }

    public ProductDTO getProduct(Long id) throws Exception {

        Optional<ProductEntity> op = this.productRepository.findById(id);
        if(op.isPresent()) {
            return this.productMapper.toDto(op.get());
        } else {
            throw new Exception("prodotto non trovato");
        }

    }

    public List<ProductDTO> getProducts() {

        List<ProductEntity> list = this.productRepository.findAll();
        return this.productMapper.toDtoList(list);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) throws Exception{
        Optional<ProductEntity> op = this.productRepository.findById(productDTO.getId());
        if (op.isPresent()){
            ProductEntity temp = productRepository.save(this.productMapper.toEntity(productDTO));
            return this.productMapper.toDto(temp);
        }
        else {
            throw new Exception("Prodotto non in catalogo");
        }
    }

    @Override
    public void removeProduct(Long id) throws Exception {
        if (!productRepository.existsById(id)) {
            throw new Exception("Prodotto da eliminare non trovato");
        }
        productRepository.deleteById(id);
    }
    @Override
    public List<ProductDTO> getProductsByTitle(String title) {
        return this.productMapper.toDtoList(this.productRepository.findByTitleContainingIgnoreCase(title));
    }
}