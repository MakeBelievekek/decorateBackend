package com.example.decorate.domain.dto;

import com.example.decorate.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductCreationFormData {

    private Long id;

    private String name;

    private String productDesc;

    private String productType;

    private String itemNumber;

    private int width;

    private int height;

    private double patternRep;

    private int price;

    private String composition;

    private String curtainType;

    private String cleaningInst;

    private List<AttributeCreationFormData> attributeCreationFormDataList = new ArrayList<>();

    private List<ImageModel> imageList = new ArrayList<>();

    private String productFamily;

    private String annotation;

    private String recommendedGlue;

    private String typeOfSize;

    private int abrasionResistance;

    public ProductCreationFormData(Wallpaper wallpaper, Curtain curtain, FurnitureFabric furnitureFabric, Decoration decoration) {
        this.id = 0L;
    }
    public ProductCreationFormData(Wallpaper wallpaper) {
        this.id = 0L;
    }

    public ProductCreationFormData(Wallpaper wallpaper, Curtain curtain) {
        this.id = wallpaper.getId();
    }

    public ProductCreationFormData(ProductKey productKey, Wallpaper wallpaper, Curtain curtain) {
        this.id = wallpaper.getId();
    }
}
