package com.example.decorate.domain.dto;

import com.example.decorate.domain.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductCategoryModalDto {
    private String productType;
    private String productDatabaseName;
    private SearchModel searchModel;
    private Set<Attribute> colorList = new HashSet<>();
    private Set<Attribute> patternList = new HashSet<>();
    private Set<Attribute> styleList = new HashSet<>();
}
