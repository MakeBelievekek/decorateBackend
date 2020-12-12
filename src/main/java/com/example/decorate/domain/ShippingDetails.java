package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ShippingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)

    private OrderHistory orderHistory;

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

    @Column(name = "time_stamp")
    private Instant timeStamp;

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
        this.timeStamp = Instant.now();
    }
}
