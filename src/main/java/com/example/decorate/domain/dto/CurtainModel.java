package com.example.decorate.domain.dto;

import com.example.decorate.domain.Curtain;
import com.example.decorate.domain.CurtainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class CurtainModel {
    private Long id;
    private String name;
    private String productDesc;
    private String curtainType;
    private int width;
    private int height;
    private String itemNumber;
    private int patternRep;
    private int price;
    private String composition;
    private String productFamily;
    private String cleaningInst;
    private String typeOfSize;
    private List<AttributeModel> attributes;
    private List<ImageData> imageList;

    public CurtainModel(Curtain curtain) {
        id = curtain.getId();
        name = curtain.getName();
        productDesc = curtain.getProductDesc();
        System.out.println(curtain.getCurtainType().toString());
        curtainType = curtain.getCurtainType().toString();
        width = curtain.getWidth();
        height = curtain.getHeight();
        itemNumber = curtain.getItemNumber();
        patternRep = curtain.getPatternRep();
        price = curtain.getPrice();
        composition = curtain.getComposition();
        productFamily = curtain.getProductFamily();
        cleaningInst = curtain.getCleaningInst();
        typeOfSize = curtain.getTypeOfSize();
    }
}
