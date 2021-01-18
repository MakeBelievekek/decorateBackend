package com.example.decorate.domain.dto.order;

import com.example.decorate.domain.BillingDetails;
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

    public BillingOrderDto(BillingDetails billingDetails) {
        this.company = billingDetails.getCompany();
        this.country = billingDetails.getCountry();
        this.address = billingDetails.getAddress1();
        this.address2 = billingDetails.getAddress2();
        this.city = billingDetails.getCity();
        this.province = billingDetails.getRegion();
        this.zip = billingDetails.getZip();
        this.phone = billingDetails.getPhoneNumber();
    }
}
