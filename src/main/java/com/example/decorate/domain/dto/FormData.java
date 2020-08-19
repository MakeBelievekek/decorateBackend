package com.example.decorate.domain.dto;

import java.util.List;

public class FormData {

    private List<AttributeFormListItem> attributes;

    public FormData() {
    }

    public FormData(List<AttributeFormListItem> attributes) {
        this.attributes = attributes;
    }

    public List<AttributeFormListItem> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeFormListItem> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "FormData{" +
                "attributeList=" + attributes +
                '}';
    }
}
