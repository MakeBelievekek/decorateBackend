package com.example.decorate.domain.dto;

import com.example.decorate.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AttributeModel {

    private Long id;

    private String type;

    private String description;

}
