package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.CategoryDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface CategoryService {

    CategoryDTO newCategory(String name);

    CategoryDTO getCategory(Long id);

    List<CategoryDTO> getCategories();

    CategoryDTO updateCategory(@Valid CategoryDTO categoryDTO);

    void removeCategory(Long id);

}
