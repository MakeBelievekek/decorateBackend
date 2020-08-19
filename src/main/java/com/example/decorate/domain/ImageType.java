package com.example.decorate.domain;

public enum ImageType {
    PRIMARY_KEY("Primary_Key"), SECONDARY_KEY("Secondary_Key");

    private String type;

    ImageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
