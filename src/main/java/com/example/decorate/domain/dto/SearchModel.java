package com.example.decorate.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SearchModel {
    private String productType;
    private String subType;
    private Long subTypeId;
    private List<Long> attributeIds = new ArrayList<>();
}
