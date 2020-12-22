package com.example.decorate.domain;

public enum ProductType {
    WALLPAPER("Tapéta", "tapeta"),
    CURTAIN("Függöny", "fuggony"),
    FURNITURE("Bútorszövet", "butorszovet"),
    DECORATION("Lakásdekoráció", "lakasdekoracio");

    private String type;
    private String typeWithoutAccent;

    ProductType(String type, String typeWithoutAccent) {
        this.type = type;
        this.typeWithoutAccent = typeWithoutAccent;
    }

    public String getType() {
        return type;
    }

    public String getTypeWithoutAccent() {
        return typeWithoutAccent;
    }
}
