package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import com.lipari.Academy2026.exceptions.ResourceNotFoundException;
import com.lipari.Academy2026.mapper.CategoryMapper;
import com.lipari.Academy2026.repository.CategoryRepository;
import com.lipari.Academy2026.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDTO newCategory(String name) {
        CategoryEntity p = new CategoryEntity();
        p.setName(name);
        p = this.categoryRepository.save(p);
        return this.categoryMapper.toDto(p);
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        return this.categoryRepository.findById(id)
                .map(this.categoryMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria con id " + id + " non trovata"));
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<CategoryEntity> list = this.categoryRepository.findAll();
        return this.categoryMapper.toDtoList(list);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if (!this.categoryRepository.existsById(categoryDTO.getId())) {
            throw new ResourceNotFoundException("Categoria con id " + categoryDTO.getId() + " non in catalogo");
        }
        CategoryEntity p = this.categoryMapper.toEntity(categoryDTO);
        CategoryEntity temp = categoryRepository.save(p);
        return this.categoryMapper.toDto(temp);
    }

    @Override
    @Transactional
    public void removeCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria da eliminare con id " + id + " non trovata");
        }
        categoryRepository.deleteById(id);
    }
}
