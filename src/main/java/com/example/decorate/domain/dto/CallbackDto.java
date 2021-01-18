package com.example.decorate.domain.dto;

import com.example.decorate.domain.dto.order.BillingOrderDto;
import com.example.decorate.domain.dto.order.ShippingOrderDto;
import com.example.decorate.domain.dto.order.UserOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CallbackDto {


    private String orderNumber;
    private String paymentStatus;
    private List<ProductListItem> productList;
    private UserOrderDto user;
    private ShippingOrderDto shipping;
    private BillingOrderDto billing;
    private String paymentOption;

}
