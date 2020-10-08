package com.example.decorate.domain.dto;

import com.example.decorate.domain.PaymentOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentOptionListItem {


    private String type;

    private Integer price;


    public PaymentOptionListItem(PaymentOption paymentOption) {

        this.type = paymentOption.getPaymentOption().getOption();
        this.price = paymentOption.getPaymentOption().getPrice();
    }

}
