package com.example.decorate.service;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.order.ItemAndQty;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {
    private BillingDetailsRepository billingDetailsRepository;
    private ShippingDetailsRepository shippingDetailsRepository;
    private ShippingOptionRepository shippingOptionRepository;
    private OrderHistoryRepository orderHistoryRepository;
    private PaymentHistoryRepository paymentHistoryRepository;
    private KeyHolderService keyHolderService;

    public OrderService(BillingDetailsRepository billingDetailsRepository, ShippingDetailsRepository shippingDetailsRepository,
                        ShippingOptionRepository shippingOptionRepository, OrderHistoryRepository orderHistoryRepository,
                        PaymentHistoryRepository paymentHistoryRepository, KeyHolderService keyHolderService) {
        this.billingDetailsRepository = billingDetailsRepository;
        this.shippingDetailsRepository = shippingDetailsRepository;
        this.shippingOptionRepository = shippingOptionRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.paymentHistoryRepository = paymentHistoryRepository;
        this.keyHolderService = keyHolderService;
    }

    public Long saveOrder(OrderDto orderDto, String orderId) {
        Long orderDatabaseId;
        int totalPrice = 0;
        List<Product> products = new ArrayList<>();
        for (ItemAndQty itemAndQty : orderDto.getItemId()) {
            products.add(new Product(this.keyHolderService.getProd(itemAndQty.getId()), itemAndQty.getQty()));
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
