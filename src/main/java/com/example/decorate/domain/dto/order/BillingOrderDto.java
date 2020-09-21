package com.example.decorate.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingOrderDto {
    private String company;
    private String country;
    private String address;
    private String address2;
    private String city;
    private String province;
    private int zip;
    private String phone;
}
