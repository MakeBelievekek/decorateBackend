package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String lastName;


    private String firstName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private ShippingDetails shippingDetails;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_id", referencedColumnName = "id")
    private BillingDetails billingDetails;

    private String orderId;

    private String email;

    private String paymentType;

    private int totalPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Product> products;

    @Column(name = "modified")
    private Instant modified;

    @Column(name = "created")
    private Instant created = Instant.now();

    public OrderHistory(OrderDto orderDto, List<Product> products, String orderId, BillingDetails billingDetails, ShippingDetails shippingDetails) {
        this.lastName = orderDto.getUser().getLastName();
        this.firstName = orderDto.getUser().getFirstName();
        this.email = orderDto.getUser().getEmail();
        this.products = products;
        this.paymentType = orderDto.getPaymentOption();
        this.orderId = orderId;
        this.billingDetails = billingDetails;
        this.shippingDetails = shippingDetails;
        this.created = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderHistory that = (OrderHistory) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
