package com.example.decorate.controller;

import com.example.decorate.domain.Product;
import com.example.decorate.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/hello")
public class Hello {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<String> getOrcFormData() {
        productRepository.save(new Product());
        return new ResponseEntity<>("Hello world!", OK);
    }

}
