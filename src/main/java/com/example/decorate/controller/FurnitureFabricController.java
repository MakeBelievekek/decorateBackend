package com.example.decorate.controller;

import com.example.decorate.domain.dto.*;
import com.example.decorate.services.furniture.FurnitureFabricService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/public/furniture")
public class FurnitureFabricController {
    private final FurnitureFabricService furnitureFabricService;

    @PostMapping
    public ResponseEntity<String> createFurnitureFabric(@RequestBody ProductCreationFormData productCreationFormData) {
        furnitureFabricService.saveFurnitureFabric(productCreationFormData);
        return ResponseEntity
                .status(CREATED)
                .body("Furniture fabric successfully created!");
    }

    @GetMapping("/{furnitureFabricId}")
    public ResponseEntity<FurnitureFabricModel> fetchFurnitureFabricById(@PathVariable Long furnitureFabricId) {
        FurnitureFabricModel furnitureFabric = furnitureFabricService.getFurnitureFabric(furnitureFabricId);

        log.info("Furniture whit id: " + furnitureFabricId + " is fetched!");

        return ResponseEntity
                .status(OK)
                .body(furnitureFabric);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FurnitureFabricModel>> fetchAllFurnitureFabric() {
        List<FurnitureFabricModel> allFurnitureFabrics = furnitureFabricService.getAllFurnitureFabrics();

        log.info(allFurnitureFabrics.size() + " wallpapers fetched from database!");

        return ResponseEntity
                .status(OK)
                .body(allFurnitureFabrics);
    }

    @PutMapping("/update/{furnitureFabricId}")
    public ResponseEntity<String> updateFurnitureFabric(@RequestBody FurnitureFabricModel furnitureFabricModel,
                                                @PathVariable Long furnitureFabricId) {
        furnitureFabricService.updateFurnitureFabric(furnitureFabricId, furnitureFabricModel);

        log.info("Furniture fabric successfully updated whit id: " + furnitureFabricId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Furniture fabric update accepted!");
    }

    @DeleteMapping("/delete/{furnitureFabricId}")
    public ResponseEntity<String> deleteWallpaper(@PathVariable Long furnitureFabricId) {
        furnitureFabricService.deleteFurnitureFabric(furnitureFabricId);

        log.info("Furniture fabric successfully deleted whit id: " + furnitureFabricId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Furniture fabric has been deleted!");
    }

    @PostMapping("/search")
    public ResponseEntity<List<FurnitureFabricModel>> search(@RequestBody SearchModel searchModel) {
        List<FurnitureFabricModel> furnitureFabricModelsForList = furnitureFabricService.getFurnitureFabricModelsForList(searchModel);

        log.info(furnitureFabricModelsForList.size() + " furnitureFabrics found form search!");

        return ResponseEntity
                .status(OK)
                .body(furnitureFabricModelsForList);
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
