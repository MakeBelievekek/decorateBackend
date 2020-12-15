package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.order.ItemAndQty;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.repositorys.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class OrderService {
    private final BillingDetailsRepository billingDetailsRepository;
    private final ShippingDetailsRepository shippingDetailsRepository;
    private final ShippingOptionRepository shippingOptionRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final PaymentHistoryRepository paymentHistoryRepository;
    private final ProductKeyService productKeyService;

    public Long saveOrder(OrderDto orderDto, String orderId) {
        Long orderDatabaseId;
        int totalPrice = 0;
        List<Product> products = new ArrayList<>();
        for (ItemAndQty itemAndQty : orderDto.getItemId()) {
            products.add(new Product(this.productKeyService.getProd(itemAndQty.getId()), itemAndQty.getQty()));
        }
        ShippingDetails shippingDetails = new ShippingDetails(orderDto);
        this.shippingDetailsRepository.save(shippingDetails);
        BillingDetails billingDetails = new BillingDetails(orderDto);
        this.billingDetailsRepository.save(billingDetails);
        OrderHistory orderHistory = new OrderHistory(orderDto, products, orderId, billingDetails, shippingDetails);
        for (Product product : products) {
            totalPrice += (product.getPrice() * product.getQuantity());
        }
        if (orderDto.getPaymentOption().equals(PaymentOptionEnum.COURSE.getOption())) {
            totalPrice += 300;
        }
        orderHistory.setTotalPrice(totalPrice + this.shippingOptionRepository.findByTypeOfDelivery(shippingDetails.getShipMethod()).getPrice());

        this.orderHistoryRepository.save(orderHistory);
        orderDatabaseId = orderHistory.getId();
        this.paymentHistoryRepository.save(new PaymentHistory(orderHistory, PaymentStatus.PENDING));
        return orderDatabaseId;
    }

}
