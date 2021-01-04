package com.example.decorate.domain.dto;

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

public class ProductAttributes {
    private List<AttributeModel> colorList = new ArrayList<>();
    private List<AttributeModel> patternList = new ArrayList<>();
    private List<AttributeModel> styleList = new ArrayList<>();
    private List<AttributeModel> compositionList = new ArrayList<>();
}
