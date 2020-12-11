package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    private double patternRep;

    private int price;

    private String composition;

    private List<String> curtainTypes = new ArrayList<>();

    private String cleaningInst;

    private List<AttributeCreationFormData> attributes = new ArrayList<>();

    private List<ImageData> imageList = new ArrayList<>();

    private String productFamily;

    private String annotation;

    private String recommendedGlue;

    private String typeOfSize;
    private int AbrasionResistance;
}
