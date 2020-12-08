package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExcelData {
    private String typeOfProduct;

    private String name;

    private String productDesc;

    private String itemNumber;

    private int width;

    private int height;

    private int patternRep;

    private int price;

    private String composition;

    private String curtainType;

    private String cleaningInst;

    private List<AttributeListItemData> attributeListItemData;

    private List<ImageData> imageList;

    private String productFamily;

    private String annotation;

    private String recommendedGlue;

    private String typeOfSize;
}
