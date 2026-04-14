package com.lipari.Academy2026.controller;

import com.lipari.Academy2026.dto.ProductDTO;
import com.lipari.Academy2026.entity.ProductEntity;
import com.lipari.Academy2026.service.ProductService;
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

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String id) {

        try {
            return new ResponseEntity<>(this.productService.getProduct(id), HttpStatus.OK);
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
    public ResponseEntity<ProductDTO> newProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(this.productService.newProduct(productDTO.getTitle(), productDTO.getDescription(), productDTO.getOldPrice(), productDTO.getPrice(), productDTO.getBrand(), productDTO.getImage(), productDTO.getIsNew(), productDTO.getCategory()));
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        try {
            if (productDTO.getId().isEmpty() || productDTO.getName().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            return ResponseEntity.ok(this.productService.updateProduct(productDTO));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        try {
            this.productService.removeProduct(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/get/{name}")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@PathVariable String name) {
        if (name != null) {
            return new ResponseEntity<>(this.productService.getProductsByName(name), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
