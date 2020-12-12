package com.example.decorate.controller;

import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.services.AttributeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/public/attribute")
public class AttributeController {
    private final AttributeService attributeService;

    @PostMapping
    public ResponseEntity<String> saveAttribute(@RequestBody AttributeCreationFormData attributeCreationFormData) {
        attributeService.saveAttribute(attributeCreationFormData);

        log.info("Attribute form data saved. Parameters: " + attributeCreationFormData.toString());

        return ResponseEntity
                .status(CREATED)
                .body("SUCCESS Attribute form data saved.");
    }

    @PostMapping("/excelAttributes")
    public ResponseEntity<String> saveAttributeFromExcel(@RequestBody List<AttributeCreationFormData> attributeCreationFormData) {
        //TODO hide the loop in the service
      /*  for (AttributeCreationFormData attributeCreationFormData : attributeCreationFormData) {
            attributeService.saveAttribute(attributeCreationFormData);
        }*/

        log.info("Attribute form data saved successfully from file.");

        return ResponseEntity
                .status(CREATED)
                .body("SUCCESS Attribute form data saved successfully from file.");
    }
}
