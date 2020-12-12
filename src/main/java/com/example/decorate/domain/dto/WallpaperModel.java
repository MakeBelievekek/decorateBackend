package com.example.decorate.domain.dto;


import com.example.decorate.domain.Wallpaper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WallpaperModel {
    private Long id;
    private String name;
    private String productDesc;
    private String itemNumber;
    private int width;
    private int height;
    private double patternRep;
    private int price;
    private String composition;
    private String productFamily;
    private String annotation;
    private String recommendedGlue;
    private List<AttributeModel> attributes;
    private List<ImageModel> imageList;

    public WallpaperModel(Wallpaper wallpaper) {
        this.id = wallpaper.getId();
        this.name = wallpaper.getName();
        this.productDesc = wallpaper.getProductDesc();
        this.itemNumber = wallpaper.getItemNumber();
        this.width = wallpaper.getWidth();
        this.height = wallpaper.getHeight();
        this.patternRep = wallpaper.getPatternRep();
        this.price = wallpaper.getPrice();
        this.composition = wallpaper.getComposition();
        this.productFamily = wallpaper.getProductFamily();
        this.annotation = wallpaper.getAnnotation();
        this.recommendedGlue = wallpaper.getRecommendedGlue();
    }
}
