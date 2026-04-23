package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.exceptions.BadRequestException;
import com.lipari.Academy2026.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<CategoryDTO> getCategory(@RequestParam Long id) {
        return ResponseEntity.ok(this.categoryService.getCategory(id));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<CategoryDTO> categories = this.categoryService.getCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> newCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new BadRequestException("Il nome della categoria non può essere vuoto");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.newCategory(categoryDTO.getName()));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        if (categoryDTO.getId() == null || categoryDTO.getId() <= 0 || categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new BadRequestException("Dati categoria non validi per l'aggiornamento");
        }
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        this.categoryService.removeCategory(id);
        return ResponseEntity.ok().build();
    }
}
