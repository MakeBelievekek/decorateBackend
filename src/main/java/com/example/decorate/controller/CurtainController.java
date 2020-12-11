package com.example.decorate.controller;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.dto.AttributeListItemData;
import com.example.decorate.domain.dto.CurtainModel;
import com.example.decorate.domain.dto.ImageData;
import com.example.decorate.domain.dto.ProductCreationFormData;
import com.example.decorate.service.CurtainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/public/curtain")
public class CurtainController {

    private final CurtainService curtainService;


    @PostMapping
    public ResponseEntity<String> createCurtain(@RequestBody ProductCreationFormData productCreationFormData) {
        curtainService.saveCurtain(productCreationFormData);

        log.info("Curtain successfully created!");

        return ResponseEntity
                .status(CREATED)
                .body("Curtain successfully created!");
    }

    @GetMapping("/{curtainId}")
    public ResponseEntity<CurtainModel> getCurtainById(@PathVariable Long curtainId) {
        CurtainModel curtain = curtainService.getCurtain(curtainId);

        log.info("Curtain whit id: " + curtainId + " is fetched!");

        return ResponseEntity
                .status(OK)
                .body(curtain);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurtainModel>> getAllCurtains() {
        List<CurtainModel> allCurtains = curtainService.getAllCurtains();

        log.info(allCurtains.size() + " curtains fetched from database!");

        return ResponseEntity
                .status(OK)
                .body(allCurtains);
    }

    @PutMapping("/update/{curtainId}")
    public ResponseEntity<String> updateCurtain(@RequestBody CurtainModel curtainModel, @PathVariable Long curtainId) {
        curtainService.updateCurtain(curtainId, curtainModel);

        log.info("Curtain successfully updated whit id: " + curtainId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Curtain update accepted!");
    }

    @DeleteMapping("/delete/{curtainId}")
    public ResponseEntity<String> deleteCurtain(@PathVariable Long curtainId) {
        curtainService.deleteCurtain(curtainId);

        log.info("Curtain successfully deleted whit id: " + curtainId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Curtain has been deleted!");
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
                        .attributeListItemData(Arrays.asList(
                                AttributeListItemData.builder()
                                        .id(1L)
                                        .build()
                        ))
                        .imageList(Arrays.asList(
                                ImageData.builder()
                                        .imageType("PRIMARY_KEY")
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
