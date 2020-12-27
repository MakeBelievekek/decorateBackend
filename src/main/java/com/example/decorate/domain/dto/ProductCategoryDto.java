package com.example.decorate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryDto {
    private SearchModel searchModel;
    private List<AttributeModel> colorList;
    private List<AttributeModel> patternList;
    private List<AttributeModel> composition;
    private List<AttributeModel> styleList;
}
