package com.example.decorate.domain.dto.order;

import com.example.decorate.domain.ShippingDetails;
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

    public ShippingOrderDto(ShippingDetails shippingOrderDto) {
        this.company = shippingOrderDto.getCompany();
        this.country = shippingOrderDto.getCountry();
        this.address = shippingOrderDto.getAddress();
        this.address2 = shippingOrderDto.getAddress2();
        this.city = shippingOrderDto.getCity();
        this.province = shippingOrderDto.getProvince();
        this.zip = shippingOrderDto.getZip();
        this.shipInfo = shippingOrderDto.getShipInfo();
        this.shipMethod = shippingOrderDto.getShipMethod();
        this.foxpost = shippingOrderDto.getFoxpost();
    }
}
