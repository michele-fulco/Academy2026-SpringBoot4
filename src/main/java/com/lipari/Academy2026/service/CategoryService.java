package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {

    public CategoryDTO newCategory(String name);

    public CategoryDTO getCategory(Long id) throws Exception;

    public List<CategoryDTO> getCategories();

    public CategoryDTO updateCategory(@Valid CategoryDTO categoryDTO) throws Exception;

    public void removeCategory(Long id) throws Exception;

}
