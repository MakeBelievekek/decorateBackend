package com.example.decorate.controller;

import com.example.decorate.domain.dto.*;
import com.example.decorate.services.AttributeService;
import com.example.decorate.services.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/public/attribute")
public class AttributeController {
    private final AttributeService attributeService;
    private final FilterService filterService;


    @PostMapping
    public ResponseEntity<String> saveAttribute(@RequestBody AttributeCreationFormData attributeCreationFormData) {
        attributeService.saveAttribute(attributeCreationFormData.getType(), attributeCreationFormData.getDescription());

        log.info("Attribute form data saved. Parameters: " + attributeCreationFormData.toString());

        return ResponseEntity
                .status(CREATED)
                .body("SUCCESS Attribute form data saved.");
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryModalDto>> getAllAttributesByType() {
        List<ProductCategoryModalDto> allAttributesByType = this.filterService.getAllAttributesByType();
        return ResponseEntity.status(OK).body(allAttributesByType);
    }


    @GetMapping("tak")
    public ResponseEntity<List<ProductCreationFormData>> search() {
        List<ProductCreationFormData> productTypeByAttribute = this.filterService.fetchAllProductOrderedByCreation();
        log.warn(productTypeByAttribute.size() + " ez a sizea");
        return ResponseEntity.status(OK).body(productTypeByAttribute);
    }

    @PostMapping("wut")
    public ResponseEntity<List<ProductCreationFormData>> search(@RequestBody SearchModel searchModel) {
        List<ProductCreationFormData> productTypeByAttribute = this.filterService.findProductByFilter(searchModel);
        log.info(productTypeByAttribute.toString());
        return ResponseEntity.status(OK).body(productTypeByAttribute);
    }

    @PostMapping("/single-filter")
    public ResponseEntity<ProductCategoryModalDto> getAttributesForSingleFilter(@RequestBody SearchModel searchModel) {
        ProductCategoryModalDto categoriesForSingleFilter = this.filterService.findAttributesBySingleFilter(searchModel);
        return ResponseEntity.status(OK).body(categoriesForSingleFilter);
    }
}
