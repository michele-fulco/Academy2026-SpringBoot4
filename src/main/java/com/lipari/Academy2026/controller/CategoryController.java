package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.CategoryDTO;
import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.CategoryEntity;
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

        try {
            return ResponseEntity.ok(this.categoryService.getCategory(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getProducts() {

        try {
            return ResponseEntity.ok(this.categoryService.getCategories());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryDTO newCategory(@RequestBody CategoryDTO categoryDTO) {

        return this.categoryService.newCategory(categoryDTO.getName());

    }
    @PutMapping("/update")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        try {
            // 1. Sostituisci il vecchio controllo IF con questo:
            if (categoryDTO.getId() == null || categoryDTO.getId() <= 0 || categoryDTO.getName().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            // 2. Chiami il service (assicurati che il service abbia @Transactional come detto prima)
            CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO);

            return ResponseEntity.ok(updatedCategory);

        } catch (Exception e) {
            // Stampa l'errore in console così se fallisce sai perché
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            this.categoryService.removeCategory(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); // <--- AGGIUNGI QUESTO per leggere l'errore nel terminale di IntelliJ/Eclipse
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Cambia in 500 per ora
        }
    }

}
