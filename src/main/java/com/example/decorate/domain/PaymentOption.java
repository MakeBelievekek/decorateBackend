package com.example.decorate.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Embeddable
public class PaymentOption {

    @Enumerated(EnumType.STRING)
    private PaymentOptionEnum paymentOption;

}
