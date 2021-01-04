package com.example.decorate.domain.dto;

import com.example.decorate.domain.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductCategoryModalDto {
    private String productType;
    private String productDatabaseName;
    private SearchModel searchModel;
    private ProductAttributes productAttributes;
}
