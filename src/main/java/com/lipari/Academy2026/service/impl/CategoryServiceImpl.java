package com.lipari.Academy2026.service.impl;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.mapper.CategoryMapper;
import com.lipari.Academy2026.repository.CategoryRepository;
import com.lipari.Academy2026.service.CategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDTO newCategory(String name) {

        CategoryEntity p = new CategoryEntity();
        p.setName(name);

        p = this.categoryRepository.save(p);

        return this.categoryMapper.toDto(p);

    }

    @Override
    public CategoryDTO getCategory(Long id) throws Exception {

        Optional<CategoryEntity> op = this.categoryRepository.findById(id);
        if(op.isPresent()) {
            return this.categoryMapper.toDto(op.get());
        } else {
            throw new Exception("prodotto non trovato");
        }

    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<CategoryEntity> list = this.categoryRepository.findAll();
        return this.categoryMapper.toDtoList(list);
    }

    @Override
    @Transactional
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) throws Exception{
        Optional<CategoryEntity> op = this.categoryRepository.findById(categoryDTO.getId());
        if (op.isPresent()){
            CategoryEntity p = this.categoryMapper.toEntity(categoryDTO);
            p.setName(categoryDTO.getName());
            CategoryEntity temp = categoryRepository.save(p);
            return this.categoryMapper.toDto(temp);
        }
        else {
            throw new Exception("Prodotto non in catalogo");
        }
    }
    @Override
    @Transactional
    public void removeCategory(Long id) throws Exception {
        if (!categoryRepository.existsById(id)) {
            throw new Exception("Prodotto da eliminare non trovato");
        }
        categoryRepository.deleteById(id);
    }
}
