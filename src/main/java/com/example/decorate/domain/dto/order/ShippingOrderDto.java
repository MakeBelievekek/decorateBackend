package com.example.decorate.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrderDto {

    private String company;
    private String country;
    private String address;
    private String address2;
    private String city;
    private String province;
    private int zip;
    private String shipInfo;
    private String shipMethod;
    private String foxpost;
}
