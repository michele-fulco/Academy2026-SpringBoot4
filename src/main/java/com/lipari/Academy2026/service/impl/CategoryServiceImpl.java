package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import com.lipari.Academy2026.mapper.CategoryMapper;
import com.lipari.Academy2026.repository.CategoryRepository;
import com.lipari.Academy2026.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    public CategoryDTO newCategory(String name) {

        CategoryEntity p = new CategoryEntity();
        p.setName(name);

        p = this.categoryRepository.save(p);

        return this.categoryMapper.toDto(p);

    }

    public CategoryDTO getCategory(String id) throws Exception {

        Optional<CategoryEntity> op = this.categoryRepository.findById(id);
        if(op.isPresent()) {
            return this.categoryMapper.toDto(op.get());
        } else {
            throw new Exception("prodotto non trovato");
        }

    }

}
