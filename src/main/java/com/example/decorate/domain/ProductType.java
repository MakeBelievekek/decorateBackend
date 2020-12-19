package com.example.decorate.domain;

public enum ProductType {
    WALLPAPER("Tapéta"),
    CURTAIN("Függöny"),
    FURNITURE_FABRIC("Bútorszövet"),
    DECORATION("Lakásdekoráció");

    private String type;

    ProductType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
