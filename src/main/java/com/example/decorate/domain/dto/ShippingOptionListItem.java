package com.example.decorate.domain.dto;

import com.example.decorate.domain.PaymentOption;
import com.example.decorate.domain.ShippingOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShippingOptionListItem {

    private String name;

    private String typeOfDelivery;

    private int price;

    private List<PaymentOptionListItem> paymentOptions;

    public ShippingOptionListItem(ShippingOption shippingOption) {
        this.name = shippingOption.getName();
        this.typeOfDelivery = shippingOption.getTypeOfDelivery();
        this.price = shippingOption.getPrice();
        List<PaymentOptionListItem> temp = new ArrayList<>();
        for (PaymentOption paymentOption : shippingOption.getPaymentOptionList()) {
            temp.add(new PaymentOptionListItem(paymentOption));
        }
        this.paymentOptions = temp;
    }
}
