package com.eccomerce_wilson.eccomerce.controller;

import com.eccomerce_wilson.eccomerce.DTO.ProductDTO;
import com.eccomerce_wilson.eccomerce.Entity.Product;
import com.eccomerce_wilson.eccomerce.Exceptions.CategoryExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.CategoryNotFoundException;
import com.eccomerce_wilson.eccomerce.Exceptions.ProductExistsException;
import com.eccomerce_wilson.eccomerce.Exceptions.ProductNotFoundException;
import com.eccomerce_wilson.eccomerce.Service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(service.getAllProduct());
    }

    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long product_id){
        Product product = service.getProductById(product_id);
        if (product==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(service.createProduct(productDTO));
    }

    @PutMapping("/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long product_id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = service.updateProduct(product_id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long product_id) {
        service.deleteProduct(product_id);
        return ResponseEntity.noContent().build();
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(ProductExistsException.class)
    public ResponseEntity<String> handleProductExistsException(ProductExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(CategoryExistsException.class)
    public ResponseEntity<String> handleCategoryExistsException(CategoryExistsException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
