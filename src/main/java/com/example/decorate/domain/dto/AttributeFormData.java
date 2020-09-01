package com.example.decorate.domain.dto;

public class AttributeFormData {


    private String type;
    private String description;

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

    @Override
    public String toString() {
        return "AttributeFormData{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
