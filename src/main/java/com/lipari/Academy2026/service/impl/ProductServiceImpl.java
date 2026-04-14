package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.mapper.ProductMapper;
import com.lipari.Academy2026.repository.ProductRepository;
import com.lipari.Academy2026.service.ProductService;
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

    public ProductDTO newProduct(String title, String description, BigDecimal oldPrice, BigDecimal price, String brand, String image, Boolean isNew, CategoryDTO category) {

        ProductEntity p = new ProductEntity();
        p.setBrand(brand);
        p.setCategory(null);
        p.setImage(null);
        p.setTitle(title);
        p.setOldPrice(oldPrice);
        p.setPrice(price);
        p.setDescription(description);

        p = this.productRepository.save(p);

        return this.productMapper.toDto(p);

    }

    public ProductDTO getProduct(String id) throws Exception {

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
    public void removeProduct(String id) throws Exception {
        Optional<ProductEntity> op = this.productRepository.findById(id);
        if (op.isPresent()){
            productRepository.deleteById(id);
        }
        else {
            throw new Exception("Prodotto da eliminare non trovato");
        }
    }

    @Override
    public List<ProductDTO> getProductsByName(String name) {
        List<ProductEntity> list = this.productRepository.findByNameContainingIgnoreCase(name);
        return this.productMapper.toDtoList(list);

    }

}
