package com.example.decorate.domain.dto;

import com.example.decorate.domain.Attribute;

public class AttributeFormListItem {
    private Long id;
    private String type;
    private String description;

    public AttributeFormListItem() {
    }

    public AttributeFormListItem(Attribute attribute) {
        this.id = attribute.getId();
        this.type = attribute.getType().getType();
        this.description = attribute.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
