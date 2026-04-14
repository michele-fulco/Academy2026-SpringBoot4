package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
import com.lipari.Academy2026.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/Category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable String id) {

        try {
            return ResponseEntity.ok(this.categoryService.getCategory(id));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/new")
    public CategoryDTO newCategory(@RequestBody CategoryDTO categoryDTO) {

        return this.categoryService.newCategory(categoryDTO.getName());

    }

}
