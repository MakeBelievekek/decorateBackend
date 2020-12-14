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

    public AttributeModel(Attribute attribute) {
        this.id = attribute.getId();
        this.type = attribute.getType().getType();
        this.description = attribute.getDescription();
    }

    public AttributeModel(AttributeItem attributeItem) {
        this.id = attributeItem.getId();
        this.type = attributeItem.getAttribute().getType().toString();
        this.description = attributeItem.getAttribute().getDescription();
    }
}
