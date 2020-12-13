package com.example.decorate.domain.dto;

import com.example.decorate.domain.Decoration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DecorationModel {
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

    public DecorationModel(Decoration decoration) {
        this.id = decoration.getId();
        this.name = decoration.getName();
        this.productDesc = decoration.getProductDesc();
        this.itemNumber = decoration.getItemNumber();
        this.width = decoration.getWidth();
        this.height = decoration.getHeight();
        this.patternRep = decoration.getPatternRep();
        this.price = decoration.getPrice();
        this.composition = decoration.getComposition();
        this.productFamily = decoration.getProductFamily();
        this.annotation = decoration.getAnnotation();
        this.recommendedGlue = decoration.getRecommendedGlue();
    }
}
