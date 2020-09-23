package com.example.decorate.domain;

import com.example.decorate.domain.dto.order.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "products", joinColumns = @JoinColumn(name = "order_history_id"))
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public ShippingDetails getShippingDetails() {
        return shippingDetails;
    }

    public void setShippingDetails(ShippingDetails shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public BillingDetails getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetails billingDetails) {
        this.billingDetails = billingDetails;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
