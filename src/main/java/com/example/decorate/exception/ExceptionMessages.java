package com.example.decorate.exception;

public enum ExceptionMessages {
    CURTAIN_NOT_EXISTS ("Curtain dose not exists!"),
    ATTRIBUTE_LIST_ITEM_NOT_EXISTS ("AttributeListItem dose not exists!"),
    IMAGE_NOT_EXISTS ("Image dose not exists!");

    private String message;

    ExceptionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
