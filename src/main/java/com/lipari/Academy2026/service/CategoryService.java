package com.lipari.Academy2026.service;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.entity.CategoryEntity;

public interface CategoryService {

    public CategoryDTO newCategory(String name);

    public CategoryDTO getCategory(Long id) throws Exception;

}
