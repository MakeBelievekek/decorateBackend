package com.example.decorate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class PaymentOption {

    @Enumerated(EnumType.STRING)
    private PaymentOptionEnum paymentOption;



}
