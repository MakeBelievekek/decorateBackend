package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.ShippingOrderDto;
import com.example.decorate.domain.dto.order.UserOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetails {
    private String company;
    private String country;
    private String city;
    private String region;
    private int zip;
    private String street;
    private String fullName;
    private String shipInfo;
    private String shipMethod;
    private String foxpost;


    public ShippingDetails(ShippingOrderDto shippingOrderDto, UserOrderDto userOrderDto) {

        this.country = shippingOrderDto.getCountry();
        this.city = shippingOrderDto.getCity();
        this.region = shippingOrderDto.getProvince();
        this.zip = shippingOrderDto.getZip();
        this.street = shippingOrderDto.getAddress() + shippingOrderDto.getAddress2();
        this.fullName = userOrderDto.getLastname() + userOrderDto.getFirstname();

    }
}
