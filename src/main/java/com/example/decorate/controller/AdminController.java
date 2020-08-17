package com.example.decorate.controller;

import com.example.decorate.domain.dto.AttributeFormData;
import com.example.decorate.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private AttributeService attributeService;

    @Autowired
    public AdminController( AttributeService attributeService) {
        this.attributeService = attributeService;
    }

   /* @PostMapping
    public ResponseEntity saveProduct(@RequestBody ProductFormData productFormData) {
        productService.saveProduct(productFormData);
        return new ResponseEntity(HttpStatus.OK);
    }
*/
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
