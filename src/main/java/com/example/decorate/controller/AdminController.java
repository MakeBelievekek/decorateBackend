package com.example.decorate.controller;

import com.example.decorate.domain.dto.CategoryListItem;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.service.CategoryService;
import com.example.decorate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ProductFormData productFormData) {
        productService.saveProduct(productFormData);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryListItem>> getAllCategory() {

        return new ResponseEntity<>(this.categoryService.getAll(), HttpStatus.OK);
    }
}
