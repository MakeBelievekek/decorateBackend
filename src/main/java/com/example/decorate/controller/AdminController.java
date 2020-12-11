package com.example.decorate.controller;

import com.example.decorate.domain.dto.AttributeFormData;
import com.example.decorate.domain.dto.FormData;
import com.example.decorate.domain.dto.ProductFormData;
import com.example.decorate.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/restricted/admin")
public class AdminController {


    private AttributeService attributeService;

    @Autowired
    public AdminController(AttributeService attributeService) {
        this.attributeService = attributeService;
    }

    @PostMapping("/attribute")
    public ResponseEntity saveAttribute(@RequestBody AttributeFormData attributeFormData) {
        attributeService.saveAttribute(attributeFormData);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/formData")
    public ResponseEntity<FormData> getFormData() {
        return new ResponseEntity(attributeService.getAll(), HttpStatus.OK);
    }

}
