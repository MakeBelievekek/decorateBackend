package com.example.decorate.domain.dto;

import com.example.decorate.domain.Curtain;
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
    private double patternRep;
    private int price;
    private String composition;
    private String productFamily;
    private String cleaningInst;
    private String typeOfSize;
    private List<AttributeModel> attributes;
    private List<ImageModel> imageList;
}
