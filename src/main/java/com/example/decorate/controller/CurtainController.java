package com.example.decorate.controller;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.dto.*;
import com.example.decorate.repositorys.curtain.CurtainRepository;
import com.example.decorate.services.curtain.CurtainService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/public/curtain")
public class CurtainController {

    private final CurtainService curtainService;
    private final CurtainRepository curtainRepository;

    @PostMapping
    public ResponseEntity<String> createCurtain(@RequestBody ProductCreationFormData productCreationFormData) {
        curtainService.saveCurtain(productCreationFormData);

        log.info("Curtain successfully created!");

        return ResponseEntity
                .status(CREATED)
                .body("Curtain successfully created!");
    }

    @GetMapping("/{curtainId}")
    public ResponseEntity<CurtainModel> fetchCurtainById(@PathVariable Long curtainId) {
        CurtainModel curtain = curtainService.getCurtain(curtainId);

        log.info("Curtain whit id: " + curtainId + " is fetched!");

        return ResponseEntity
                .status(OK)
                .body(curtain);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurtainModel>> fetchAllCurtains() {
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

    @PostMapping("/search")
    public ResponseEntity<List<CurtainModel>> search(@RequestBody SearchModel searchModel) {
        List<CurtainModel> curtainModelsForList = curtainService.getCurtainModelsForList(searchModel);

        log.info("Curtain successfully created!");

        return ResponseEntity
                .status(OK)
                .body(curtainModelsForList);
    }

    @GetMapping("/dummy")
    public ResponseEntity<List<Curtain>> search() {
        List<Curtain> findwhateva = curtainRepository.findwhateva();

        log.info("Curtain successfully created!");

        return ResponseEntity
                .status(OK)
                .body(findwhateva);
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
                        .abrasionResistance(500)
                        .curtainType("BLACKOUT")
                        .cleaningInst("balek")
                        .attributeCreationFormDataList(Arrays.asList(
                                AttributeCreationFormData.builder()
                                        .type("COLOR")
                                        .description("kek")
                                        .build()
                        ))
                        .imageList(Arrays.asList(
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
    @GetMapping("/dummyAttr")
    public ResponseEntity<List<Curtain>> getCurtainsByAttr(){
        return ResponseEntity.status(OK).body(curtainService.getCurtainByAttr());
    }

    @GetMapping("/search")
    public ResponseEntity<SearchModel> xxx() {
        List<AttributeModel> build = Collections.singletonList(AttributeModel.builder()
                .description("kek")
                .type("COLOR")
                .id(1L)
                .build());
        SearchModel retek = SearchModel.builder()
                .productId(1L)
                .attributes(build)
                .build();
        return ResponseEntity
                .status(OK)
                .body(retek);
    }

}
