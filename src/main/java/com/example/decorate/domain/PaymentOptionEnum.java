package com.example.decorate.domain;

public enum PaymentOptionEnum {

    CASH("Készpénzes fizetés", 0), CREDIT("Barion bankkártyás fizetés", 0), COURSE("Utánvétes fizetés", 300), FORWARD_REFERENCES("Előre utalás", 0);

    private String option;
    private Integer price;

    PaymentOptionEnum(String option, Integer price
    ) {
        this.option = option;
        this.price = price;
    }

    public String getOption() {
        return option;
    }

    public Integer getPrice() {
        return price;
    }
}
