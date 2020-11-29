package com.example.decorate.domain.dto;

import com.example.decorate.domain.dto.order.ShippingOrderDto;
import com.example.decorate.domain.dto.order.UserOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShippingOrder {

    private String country;

    private String city;

    private String region;

    private int zip;

    private String street;

    private String fullName;

    public ShippingOrder(ShippingOrderDto shippingOrderDto, UserOrderDto userOrderDto) {
        if (shippingOrderDto.getCountry().equals("Magyarország")) {
            this.country = "HU";
        }
        this.city = shippingOrderDto.getCity();
        this.region = shippingOrderDto.getProvince();
        this.zip = shippingOrderDto.getZip();
        this.street = shippingOrderDto.getAddress() + shippingOrderDto.getAddress2();
        this.fullName = userOrderDto.getLastname() + userOrderDto.getFirstname();

    }
}
