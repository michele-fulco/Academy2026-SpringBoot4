package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    // Endpoint per lo Shop Pubblico
    @GetMapping("/active")
    public ResponseEntity<List<ProductDTO>> getActiveProducts() {
        try {
            return ResponseEntity.ok(this.productService.getActiveProducts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

// GET /products rimane per la Dashboard Admin

    @GetMapping("/product")
    public ResponseEntity<ProductDTO> getProduct(@RequestParam Long t) {

        try {
            return new ResponseEntity<>(this.productService.getProduct(t), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {

        try {
            return ResponseEntity.ok(this.productService.getProducts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PostMapping("/new")
    public ResponseEntity<ProductDTO> newProduct(@Valid @RequestBody ProductDTO productDTO) {
        try {
            return ResponseEntity.ok(this.productService.newProduct(productDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            // 1. Sostituisci il vecchio controllo IF con questo:
            if (productDTO.getId() == null || productDTO.getId() <= 0 || productDTO.getTitle().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            // 2. Chiami il service (assicurati che il service abbia @Transactional come detto prima)
            ProductDTO updatedProduct = this.productService.updateProduct(productDTO);

            return ResponseEntity.ok(updatedProduct);

        } catch (Exception e) {
            // Stampa l'errore in console così se fallisce sai perché
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            this.productService.removeProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); // <--- AGGIUNGI QUESTO per leggere l'errore nel terminale di IntelliJ/Eclipse
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Cambia in 500 per ora
        }
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> softDeletion(@PathVariable Long id) {
        try {
            this.productService.softDeletion(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); // <--- AGGIUNGI QUESTO per leggere l'errore nel terminale di IntelliJ/Eclipse
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Cambia in 500 per ora
        }
    }
    @GetMapping("/get/{title}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String title) {
        if (title != null) {
            return new ResponseEntity<>(this.productService.getProductsByTitle(title), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
