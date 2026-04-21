package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    public CategoryDTO newCategory(String name);

    public CategoryDTO getCategory(Long id) throws Exception;

    public List<CategoryDTO> getCategories();
}
