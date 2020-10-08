package com.example.decorate.domain.dto;

import com.example.decorate.domain.PaymentOption;
import com.example.decorate.domain.ShippingOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShippingOptionListItem {

    String name;
    String typeOfDelivery;
    int price;
    List<PaymentOptionListItem> paymentOptions;

    public ShippingOptionListItem() {
    }

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
