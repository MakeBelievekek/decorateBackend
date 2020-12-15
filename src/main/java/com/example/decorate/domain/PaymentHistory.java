package com.example.decorate.domain;

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

    @Column(name = "time_stamp")
    private Instant timeStamp;

    public PaymentHistory(OrderHistory orderHistory, PaymentStatus paymentStatus) {
        this.orderHistory = orderHistory;
        this.status = paymentStatus;
        this.timeStamp = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentHistory that = (PaymentHistory) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
