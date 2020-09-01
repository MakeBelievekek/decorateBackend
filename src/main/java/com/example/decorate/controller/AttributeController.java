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
@RequestMapping("/attribute")
public class AttributeController {
    private AttributeService attributeService;

    @Autowired
    public AttributeController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping
    public ResponseEntity saveAttribute(@RequestBody AttributeFormData attributeFormData) {
        attributeService.saveAttribute(attributeFormData);
        System.out.println(attributeFormData);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
