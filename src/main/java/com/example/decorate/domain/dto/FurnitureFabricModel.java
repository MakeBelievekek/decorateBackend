package com.example.decorate.domain.dto;

import com.example.decorate.domain.FurnitureFabric;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FurnitureFabricModel {
    private Long id;
    private String name;
    private String productDesc;
    private int width;
    private int height;
    private String itemNumber;
    private double patternRep;
    private int price;
    private String composition;
    private String productFamily;
    private String cleaningInst;
    private int abrasionResistance;
    private String typeOfSize;
    private List<AttributeModel> attributes;
    private List<ImageModel> imageList;

    public FurnitureFabricModel(FurnitureFabric furnitureFabric) {
        this.id = furnitureFabric.getId();
        this.name = furnitureFabric.getName();
        this.productDesc = furnitureFabric.getProductDesc();
        this.width = furnitureFabric.getWidth();
        this.height = furnitureFabric.getHeight();
        this.itemNumber = furnitureFabric.getItemNumber();
        this.patternRep = furnitureFabric.getPatternRep();
        this.price = furnitureFabric.getPrice();
        this.composition = furnitureFabric.getComposition();
        this.productFamily = furnitureFabric.getProductFamily();
        this.cleaningInst = furnitureFabric.getCleaningInst();
        this.abrasionResistance = furnitureFabric.getAbrasionResistance();
        this.typeOfSize = furnitureFabric.getTypeOfSize();
    }
}
