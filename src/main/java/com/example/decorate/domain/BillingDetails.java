package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String company;

    private String country;

    private String city;

    private String region;

    private int zip;
    @Column(name = "utca_házszám")
    private String address1;
    @Column(name = "emelet_ajtó")
    private String address2;

    private String phoneNumber;

    public BillingDetails(OrderDto orderDto) {
        this.company = orderDto.getBilling().getCompany();
        this.country = orderDto.getBilling().getCountry();
        this.city = orderDto.getBilling().getCity();
        this.region = orderDto.getBilling().getProvince();
        this.zip = orderDto.getBilling().getZip();
        this.address1 = orderDto.getBilling().getAddress();
        this.address2 = orderDto.getBilling().getAddress2();
        this.phoneNumber = orderDto.getBilling().getPhone();
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
