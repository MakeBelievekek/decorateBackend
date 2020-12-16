package com.example.decorate.controller;

import com.example.decorate.domain.dto.*;
import com.example.decorate.services.wallpaper.WallpaperService;
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
@RequestMapping("/api/public/wallpaper")

public class WallpaperController {
    private final WallpaperService wallpaperService;

    @PostMapping
    public ResponseEntity<String> createWallpaper(@RequestBody ProductCreationFormData productCreationFormData) {
        wallpaperService.saveWallpaper(productCreationFormData);
        return ResponseEntity
                .status(CREATED)
                .body("Wallpaper successfully created!");
    }

    @GetMapping("/{wallpaperId}")
    public ResponseEntity<WallpaperModel> fetchWallpaperById(@PathVariable Long wallpaperId) {
        WallpaperModel wallpaper = wallpaperService.getWallpaper(wallpaperId);

        log.info("Wallpaper whit id: " + wallpaperId + " is fetched!");

        return ResponseEntity
                .status(OK)
                .body(wallpaper);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WallpaperModel>> fetchAllWallpapers() {
        List<WallpaperModel> allWallpapers = wallpaperService.getAllWallpapers();

        log.info(allWallpapers.size() + " wallpapers fetched from database!");

        return ResponseEntity
                .status(OK)
                .body(allWallpapers);
    }

    @PutMapping("/update/{wallpaperId}")
    public ResponseEntity<String> updateWallpaper(@RequestBody WallpaperModel wallpaperModel, @PathVariable Long wallpaperId) {
        wallpaperService.updateWallpaper(wallpaperId, wallpaperModel);

        log.info("Wallpaper successfully updated whit id: " + wallpaperId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Wallpaper update accepted!");
    }

    @DeleteMapping("/delete/{wallpaperId}")
    public ResponseEntity<String> deleteWallpaper(@PathVariable Long wallpaperId) {
        wallpaperService.deleteWallpaper(wallpaperId);

        log.info("Wallpaper successfully deleted whit id: " + wallpaperId);

        return ResponseEntity
                .status(ACCEPTED)
                .body("Wallpaper has been deleted!");
    }

    @PostMapping("/search")
    public ResponseEntity<List<WallpaperModel>> search(@RequestBody SearchModel searchModel) {
        List<WallpaperModel> wallpaperModelsForList = wallpaperService.getWallpaperModelsForList(searchModel);

        log.info(wallpaperModelsForList.size() + " wallpapers found form search!");

        return ResponseEntity
                .status(OK)
                .body(wallpaperModelsForList);
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
