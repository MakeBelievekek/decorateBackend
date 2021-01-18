package com.example.decorate.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAndOrderDto {
    private String paymentStatus;
    private String orderId;
}
