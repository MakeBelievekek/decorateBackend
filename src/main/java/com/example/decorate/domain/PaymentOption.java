package com.example.decorate.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class PaymentOption {

    @Enumerated(EnumType.STRING)
    private PaymentOptionEnum paymentOption;

    public PaymentOptionEnum getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(PaymentOptionEnum paymentOption) {
        this.paymentOption = paymentOption;
    }
}
