package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String company;
    private String country;
    private String city;
    private String province;
    private int zip;
    private String address;
    private String address2;
    private String shipInfo;
    private String shipMethod;
    private String foxpost;

    public ShippingDetails(OrderDto orderDto) {
        this.company = orderDto.getShipping().getCompany();
        this.country = orderDto.getShipping().getCountry();
        this.city = orderDto.getShipping().getCity();
        this.province = orderDto.getShipping().getProvince();
        this.zip = orderDto.getShipping().getZip();
        this.address = orderDto.getShipping().getAddress();
        this.address2 = orderDto.getShipping().getAddress2();
        this.shipInfo = orderDto.getShipping().getShipInfo();
        this.shipMethod = orderDto.getShipping().getShipMethod();
        this.foxpost = orderDto.getShipping().getFoxpost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getShipInfo() {
        return shipInfo;
    }

    public void setShipInfo(String shipInfo) {
        this.shipInfo = shipInfo;
    }

    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    public String getFoxpost() {
        return foxpost;
    }

    public void setFoxpost(String foxpost) {
        this.foxpost = foxpost;
    }
}
