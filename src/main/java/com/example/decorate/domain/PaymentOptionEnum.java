package com.example.decorate.domain;

public enum PaymentOptionEnum {

    CASH("Készpénzes fizetés"),CREDIT("Barion bankkártyás fizetés"),COURSE("Utánvétes fizetés"),FORWARD_REFERENCES("Előre utalás");

    private String option;

    PaymentOptionEnum(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }
}
