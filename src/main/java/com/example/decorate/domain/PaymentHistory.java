package com.example.decorate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderHistory orderHistory;

    private String paymentId;

    private String paymentRequestId;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public PaymentHistory(OrderHistory orderHistory, PaymentStatus paymentStatus) {
        this.orderHistory = orderHistory;
        this.status = paymentStatus;
    }
}
