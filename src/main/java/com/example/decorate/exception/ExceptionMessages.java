package com.example.decorate.exception;

public enum ExceptionMessages {
    CURTAIN_NOT_EXISTS ("Curtain dose not exists!"),
    ATTRIBUTE_LIST_ITEM_NOT_EXISTS ("AttributeListItem dose not exists!"),
    IMAGE_NOT_EXISTS ("Image dose not exists!"),
    WALLPAPER_NOT_EXISTS ("Wallpaper dose not exists!"),
    FURNITURE_FABRIC_NOT_EXISTS ("Furniture fabric dose not exists!"),
    ATTRIBUTE_NOT_EXISTS ("Attribute dose not exists!"),
    ATTRIBUTE_LIST_EMPTY ("You must fill out the product attribute list!"),
    MULTIPLE_PRIMARY_IMG_EXISTS ("Multiple PRIMARY_IMG exists!"),
    DECORATION_NOT_EXISTS ("Decoration dose not exists!"),
    PRODUCT_NOT_EXISTS ("Desired product dose not exists!");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
