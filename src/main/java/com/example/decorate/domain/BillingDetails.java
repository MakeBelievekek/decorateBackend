package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "billingDetails")
public class BillingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    private OrderHistory orderHistory;
    private String company;

    private String country;

    private String city;

    private String region;

    private int zip;
    @Column(name = "utca")
    private String address1;
    @Column(name = "emelet")
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

}
