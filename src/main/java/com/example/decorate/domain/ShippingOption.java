package com.example.decorate.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShippingOption {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String typeOfDelivery;

    @ElementCollection
    private List<PaymentOption> paymentOptionList;

    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeOfDelivery() {
        return typeOfDelivery;
    }

    public List<PaymentOption> getPaymentOptionList() {
        return paymentOptionList;
    }

    public void setPaymentOptionList(List<PaymentOption> paymentOptionList) {
        this.paymentOptionList = paymentOptionList;
    }

    public void setTypeOfDelivery(String type) {
        this.typeOfDelivery = type;
    }

    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }
}
