package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
@Table(name = "billingDetails")
public class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
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

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    public BillingDetails(OrderDto orderDto) {
        this.company = orderDto.getBilling().getCompany();
        this.country = orderDto.getBilling().getCountry();
        this.city = orderDto.getBilling().getCity();
        this.region = orderDto.getBilling().getProvince();
        this.zip = orderDto.getBilling().getZip();
        this.address1 = orderDto.getBilling().getAddress();
        this.address2 = orderDto.getBilling().getAddress2();
        this.phoneNumber = orderDto.getBilling().getPhone();
        this.created = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingDetails that = (BillingDetails) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
