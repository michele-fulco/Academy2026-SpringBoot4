package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    ProductDTO getProduct(Long id);

    List<ProductDTO> getProducts();

    ProductDTO updateProduct(ProductDTO productDTO);

    void removeProduct(Long id);

    List<ProductDTO> getProductsByTitle(String title);

    ProductDTO newProduct(ProductDTO productDTO);

    void softDeletion(Long id);

    List<ProductDTO> getActiveProducts();
}
