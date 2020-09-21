package com.example.decorate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Embedded
    private ShippingDetails shippingDetails;
    private String email;
    private String paymentType;
    @ElementCollection
    private List<Product> products;

}
