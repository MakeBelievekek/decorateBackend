package com.example.decorate.domain.dto;

import com.example.decorate.domain.Attribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AttributeFormListItem {

    private Long id;

    private String type;

    private String description;

    public AttributeFormListItem(Attribute attribute) {
        this.id = attribute.getId();
        this.type = attribute.getType().getType();
        this.description = attribute.getDescription();
    }
}
