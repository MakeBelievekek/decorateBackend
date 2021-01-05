package com.example.decorate.domain.dto;

import com.example.decorate.domain.BillingDetails;
import com.example.decorate.domain.OrderHistory;
import com.example.decorate.domain.PaymentHistory;
import com.example.decorate.domain.ShippingDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetails {

    private PaymentHistory paymentHistory;
    private OrderHistory orderHistory;
    private ShippingDetails shippingDetails;
    private BillingDetails billingDetails;
}
