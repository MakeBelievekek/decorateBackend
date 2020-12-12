package com.example.decorate.controller;

import com.example.decorate.domain.dto.*;
import com.example.decorate.services.decoration.DecorationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/public/decoration")
public class DecorationController {
    private final DecorationService decorationService;

    @PostMapping
    public ResponseEntity<String> createDecoration(@RequestBody ProductCreationFormData productCreationFormData) {
        decorationService.saveDecoration(productCreationFormData);
        return ResponseEntity
                .status(CREATED)
                .body("Decoration successfully created!");
    }

    @GetMapping("/{decorationId}")
    public ResponseEntity<DecorationModel> fetchDecorationById(@PathVariable Long decorationId) {
        DecorationModel decoration = decorationService.getDecoration(decorationId);

        log.info("Decoration whit id: " + decorationId + " is fetched!");

        return ResponseEntity
                .status(OK)
                .body(decoration);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DecorationModel>> fetchAllDecorations() {
        List<DecorationModel> allDecorations = decorationService.getAllDecorations();

        log.info(allDecorations.size() + " decoration fetched from database!");

        return ResponseEntity
                .status(OK)
                .body(allDecorations);
    }

    @PutMapping("/update/{decorationId}")
    public ResponseEntity<String> updateDecoration(@RequestBody DecorationModel decorationModel,
                                                   @PathVariable Long decorationId) {
        decorationService.updateDecoration(decorationId, decorationModel);

        log.info("Decoration successfully updated whit id: " + decorationId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Decoration update accepted!");
    }

    @DeleteMapping("/delete/{decorationId}")
    public ResponseEntity<String> deleteDecoration(@PathVariable Long decorationId) {
        decorationService.deleteDecoration(decorationId);

        log.info("Decoration successfully deleted whit id: " + decorationId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Decoration has been deleted!");
    }

    @GetMapping
    public ResponseEntity<ProductCreationFormData> controllDto() {
        return ResponseEntity
                .status(OK)
                .body(ProductCreationFormData.builder()
                        .name("japan black")
                        .productDesc("hosszu és kimeritő szöveg")
                        .itemNumber("1345")
                        .width(325)
                        .height(500)
                        .patternRep(15)
                        .price(5000)
                        .composition("bársony")
                        .curtainType("BLACKOUT")
                        .cleaningInst("balek")
                        .abrasionResistance(500)
                        .attributeCreationFormDataList(Collections.singletonList(
                                AttributeCreationFormData.builder()
                                        .type("COLOR")
                                        .description("szép")
                                        .build()
                        ))
                        .imageList(Collections.singletonList(
                                ImageModel.builder()
                                        .imageType("PRIMARY_IMG")
                                        .imgUrl("valami.hu")
                                        .build()
                        ))
                        .productFamily("japan")
                        .annotation("kockás")
                        .recommendedGlue("erős")
                        .typeOfSize("nagy")
                        .build());
    }
}
