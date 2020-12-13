package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 3, max = 15)
    @NotBlank(message = "Kérlek adj meg egy vezetéknevet")
    private String lastName;

    @Size(min = 3, max = 15)
    @NotBlank(message = "Kérlek adj meg egy keresztnevet")
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
        this.lastName = orderDto.getUser().getLastname();
        this.firstName = orderDto.getUser().getFirstname();
        this.email = orderDto.getUser().getEmail();
      this.products = products;
        this.paymentType = orderDto.getPaymentOption();
        this.orderId = orderId;
        this.billingDetails = billingDetails;
        this.shippingDetails = shippingDetails;
        this.created = Instant.now();
    }
}
