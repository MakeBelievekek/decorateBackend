package com.example.decorate.domain.dto;

import com.example.decorate.domain.PaymentOption;
import com.example.decorate.domain.ShippingOption;

import java.util.ArrayList;
import java.util.List;

public class ShippingOptionListItem {

    String name;
    String typeOfDelivery;
    int price;
    List<String> paymentOptions;

    public ShippingOptionListItem() {
    }

    public ShippingOptionListItem(ShippingOption shippingOption) {
        this.name = shippingOption.getName();
        this.typeOfDelivery = shippingOption.getTypeOfDelivery();
        this.price = shippingOption.getPrice();
        List<String> temp = new ArrayList<>();
        for (PaymentOption paymentOption : shippingOption.getPaymentOptionList()) {
            temp.add(paymentOption.getPaymentOption().getOption());
        }
        this.paymentOptions = temp;
    }

    public List<String> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<String> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public String getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public void setTypeOfDelivery(String typeOfDelivery) {
        this.typeOfDelivery = typeOfDelivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
