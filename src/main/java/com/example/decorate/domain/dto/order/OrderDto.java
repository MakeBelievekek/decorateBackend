package com.example.decorate.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private List<ItemAndQty> itemId;
    private UserOrderDto user;
    private ShippingOrderDto shipping;
    private BillingOrderDto billing;
    private String paymentOption;

}
