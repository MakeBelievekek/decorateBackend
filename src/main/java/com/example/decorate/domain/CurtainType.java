package com.example.decorate.domain;

public enum CurtainType {
    BLACKOUT("Blackout"), CHILDREN("Children"), TRANSLUCENT("Translucent"), DARKENER("Darkener");

    private String type;

    CurtainType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
