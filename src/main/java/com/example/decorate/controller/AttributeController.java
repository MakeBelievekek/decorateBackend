package com.example.decorate.controller;

import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.ProductCategoryDto;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.domain.dto.SearchModel;
import com.example.decorate.services.AttributeItemService;
import com.example.decorate.services.AttributeService;
import com.example.decorate.services.FilterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

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
    public ResponseEntity<List<ProductCategoryDto>> getAllAttributesByType() {
        List<ProductCategoryDto> allAttributesByType = this.filterService.getAllAttributesByType();
        return ResponseEntity.status(OK).body(allAttributesByType);
    }

    @PostMapping("wut")
    public ResponseEntity<List<ProductCreationFormData>> search(@RequestBody SearchModel searchModel) {
        List<ProductCreationFormData> productTypeByAttribute = this.filterService.findProductTypeByAttribute(searchModel);
        return ResponseEntity.status(OK).body(productTypeByAttribute);
    }
}
