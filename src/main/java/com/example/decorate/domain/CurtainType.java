package com.example.decorate.domain;

public enum CurtainType {
    BLACKOUT("Blackout Függöny"),
    CHILDREN("Gyerekfüggöny"),
    TRANSLUCENT("Fényáteresztő Függöny"),
    DARKENER("Sötétítő Függöny"),
    DEKOR("Dekorfüggöny");

    private String type;

    CurtainType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
