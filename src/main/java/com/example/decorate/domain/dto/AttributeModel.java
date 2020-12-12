package com.example.decorate.domain.dto;

import com.example.decorate.domain.Attribute;
import com.example.decorate.domain.AttributeListItem;
import com.example.decorate.domain.CurtainAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributeModel {

    private Long id;

    private String type;

    private String description;

    public AttributeModel(Attribute attribute) {
        this.id = attribute.getId();
        this.type = attribute.getType().getType();
        this.description = attribute.getDescription();
    }

    public AttributeModel(AttributeListItem attributeListItem) {
        this.id = attributeListItem.getId();
        this.type = attributeListItem.getAttribute().getType().toString();
        this.description = attributeListItem.getAttribute().getDescription();
    }

    public AttributeModel(CurtainAttribute curtainAttribute) {
        this.id = curtainAttribute.getId();
        this.type = curtainAttribute.getAttribute().getType().toString();
        this.description = curtainAttribute.getAttribute().getDescription();
    }
}
