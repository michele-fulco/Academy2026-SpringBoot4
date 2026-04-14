package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    

    public ProductDTO getProduct(String id) throws Exception;

    public List<ProductDTO> getProducts();

    public ProductDTO updateProduct(ProductDTO productDTO) throws Exception;

    public void removeProduct(String id) throws Exception;

    public List<ProductDTO> getProductsByName(String name);

    public ProductDTO newProduct(String title, String description, BigDecimal oldPrice, BigDecimal price, String brand, String image, Boolean isNew, CategoryDTO category);
}
