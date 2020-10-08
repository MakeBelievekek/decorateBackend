package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String lastName;
    private String firstName;
    @OneToOne
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private ShippingDetails shippingDetails;
    @OneToOne
    @JoinColumn(name = "billing_id", referencedColumnName = "id")
    private BillingDetails billingDetails;
    private String orderId;
    private String email;
    private String paymentType;
    private int totalPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Product> products;

    public OrderHistory(OrderDto orderDto, List<Product> products, String orderId, BillingDetails billingDetails, ShippingDetails shippingDetails) {
        this.lastName = orderDto.getUser().getLastname();
        this.firstName = orderDto.getUser().getFirstname();
        this.email = orderDto.getUser().getEmail();
        this.products = products;
        this.paymentType = orderDto.getPaymentOption();
        this.orderId = orderId;
        this.billingDetails = billingDetails;
        this.shippingDetails = shippingDetails;
    }


}
