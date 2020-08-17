package com.example.decorate.controller;

import com.example.decorate.domain.dto.AttributeFormData;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.service.AttributeService;
import com.example.decorate.service.CategoryService;
import com.example.decorate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;
    private CategoryService categoryService;
    private AttributeService attributeService;

    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService, AttributeService attributeService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.attributeService = attributeService;
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ProductFormData productFormData) {
        productService.saveProduct(productFormData);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/attribute")
    public ResponseEntity saveAttribute(@RequestBody AttributeFormData attributeFormData) {
        attributeService.saveAttribute(attributeFormData);
        return new ResponseEntity(HttpStatus.OK);
    }

   /* @GetMapping("/categories/getAll")
    public ResponseEntity<List<CategoryListItem>> getAllCategory() {

        return new ResponseEntity<>(this.categoryService.getAll(), HttpStatus.OK);
    }*/
}
