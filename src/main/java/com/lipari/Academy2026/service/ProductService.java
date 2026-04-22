package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {


    public ProductDTO getProduct(Long id) throws Exception;

    public List<ProductDTO> getProducts();

    public ProductDTO updateProduct(ProductDTO productDTO) throws Exception;

    public void removeProduct(Long id) throws Exception;

    public List<ProductDTO> getProductsByTitle(String title);

    public ProductDTO newProduct(ProductDTO productDTO);

    public void softDeletion(Long id) throws Exception;
}
