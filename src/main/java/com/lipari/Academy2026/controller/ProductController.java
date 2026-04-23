package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.exceptions.BadRequestException;
import com.lipari.Academy2026.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/active")
    public ResponseEntity<List<ProductDTO>> getActiveProducts() {
        List<ProductDTO> products = this.productService.getActiveProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product")
    public ResponseEntity<ProductDTO> getProduct(@RequestParam Long t) {
        return ResponseEntity.ok(this.productService.getProduct(t));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = this.productService.getProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> newProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productService.newProduct(productDTO));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        if (productDTO.getId() == null || productDTO.getId() <= 0 || productDTO.getTitle() == null || productDTO.getTitle().isEmpty()) {
            throw new BadRequestException("Dati prodotto non validi per l'aggiornamento");
        }
        return ResponseEntity.ok(this.productService.updateProduct(productDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        this.productService.removeProduct(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> softDeletion(@PathVariable Long id) {
        this.productService.softDeletion(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{title}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String title) {
        List<ProductDTO> products = this.productService.getProductsByTitle(title);
        return ResponseEntity.ok(products);
    }
}
