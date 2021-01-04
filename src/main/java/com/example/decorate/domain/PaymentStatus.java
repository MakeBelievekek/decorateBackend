package com.example.decorate.domain;

public enum PaymentStatus {
    PENDING("Pending"), SUCCEEDED("Succeeded"), CANCELED("Canceled");

    private String type;

    PaymentStatus(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
