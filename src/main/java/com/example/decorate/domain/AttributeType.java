package com.example.decorate.domain;

public enum AttributeType {
    COLOR("Color"),
    PATTERN("Pattern"),
    STYLE("Style"),
    COMPOSITION("Composition"),
    TYPE("Type");

    private String type;

    AttributeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
