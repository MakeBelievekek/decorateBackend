package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductCreationFormData {

    private String name;

    private String productDesc;

    private String itemNumber;

    private int width;

    private int height;

    private double patternRep;

    private int price;

    private String composition;

    private String curtainType;

    private String cleaningInst;

    private List<AttributeCreationFormData> attributeListItemData;

    private List<ImageModel> imageList;

    private String productFamily;

    private String annotation;

    private String recommendedGlue;

    private String typeOfSize;
}
