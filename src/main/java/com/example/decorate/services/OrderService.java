package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.ProductListItem;
import com.example.decorate.domain.dto.order.OrderDto;
import com.example.decorate.repositorys.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.decorate.domain.PaymentOptionEnum.CASH_ON_DELIVERY;

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


    public List<Long> getProductIds(OrderDto orderDto) {
        List<Long> ids = new ArrayList<>();
        orderDto.getItemId().forEach(itemAndQty -> ids.add(itemAndQty.getId()));
        return ids;
    }

    public Long saveOrder(OrderDto orderDto, String orderId) {
        System.out.println(orderDto);
        Long orderDatabaseId;
        int totalPrice = 0;
        List<Product> products = new ArrayList<>();
        orderDto.getItemId()
                .forEach(itemAndQty -> {
                    ProductListItem prod = this.productKeyService.getProd(itemAndQty.getId());
                    products.add(new Product(prod, itemAndQty.getQty()));
                });
        ShippingDetails shippingDetails = new ShippingDetails(orderDto);
        this.shippingDetailsRepository.save(shippingDetails);
        BillingDetails billingDetails = new BillingDetails(orderDto);
        this.billingDetailsRepository.save(billingDetails);
        OrderHistory orderHistory = new OrderHistory(orderDto, products, orderId, billingDetails, shippingDetails);
        for (Product product : products) {
            totalPrice += (product.getPrice() * product.getQuantity());
        }
        if (orderDto.getPaymentOption().equals(CASH_ON_DELIVERY.getOption())) {
            totalPrice += CASH_ON_DELIVERY.getPrice();
        }
        orderHistory.setTotalPrice(totalPrice + this.shippingOptionRepository.findByTypeOfDelivery(shippingDetails.getShipMethod()).getPrice());

        this.orderHistoryRepository.save(orderHistory);
        orderDatabaseId = orderHistory.getId();
        shippingDetails.setOrderHistory(orderHistory);
        billingDetails.setOrderHistory(orderHistory);
        this.paymentHistoryRepository.save(new PaymentHistory(orderHistory, PaymentStatus.PENDING));
        return orderDatabaseId;
    }

    public OrderHistory findOrder(Long id) {
        if (this.orderHistoryRepository.findById(id).isPresent())
            return this.orderHistoryRepository.findById(id).get();
        else
            return null;
    }

    public ShippingDetails findShippingDetails(Long id) {
        return this.shippingDetailsRepository.findByOrderId(id);
    }

    public BillingDetails findBillingDetails(Long id) {

        return this.billingDetailsRepository.findByOrderId(id);
    }
}
