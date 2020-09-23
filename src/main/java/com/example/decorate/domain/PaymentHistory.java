package com.example.decorate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
